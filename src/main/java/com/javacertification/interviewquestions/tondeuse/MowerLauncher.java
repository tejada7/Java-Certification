package com.javacertification.interviewquestions.tondeuse;

import com.javacertification.interviewquestions.tondeuse.business.enums.Action;
import com.javacertification.interviewquestions.tondeuse.business.enums.Orientation;
import com.javacertification.interviewquestions.tondeuse.exceptions.InvalidActionException;
import com.javacertification.interviewquestions.tondeuse.model.Mower;
import com.javacertification.interviewquestions.tondeuse.model.Position;
import com.javacertification.interviewquestions.tondeuse.view.Drawer;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.jooq.lambda.Unchecked;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.javacertification.interviewquestions.tondeuse.model.Field.maxSizeX;
import static com.javacertification.interviewquestions.tondeuse.model.Field.maxSizeY;
import static org.apache.commons.lang3.StringUtils.SPACE;

/**
 * This program takes two arguments as input: the first one refers to the absolute path where the text file containing
 * the directions is located, the second argument (called --graphical) is optional and allows to display a UI to
 * simulate the mower.
 * As per the text file, the first line corresponds to the coordinates of the upper right corner of the lawn, those
 * from the bottom left corner are assumed to be (0,0)
 * - The rest of the file allows to control all the mowers that have been deployed. Each
 * mower with two lines concerning it:
 * - the first line gives the initial position of the mower, as well as its orientation. The
 * position and orientation are provided in the form of 2 digits and a letter, separated by a space
 * - the second line is a series of instructions ordering the mower to explore the lawn.
 * The instructions are a sequence of characters without spaces.
 */
@Slf4j
public final class MowerLauncher {

    public static void main(final String[] args) {
        new MowerLauncher().run(args);
    }

    public void run(final String... args) {
        if (args.length == 0) {
            log.warn("No arguments are provided. Please provide the path file name containing the input.");
            return;
        }
        Try.of(() -> processInput(Files.readAllLines(Paths.get(args[0])), getGraphicalArgument(args)))
                .onSuccess(results -> results.forEach(log::info))
                .onFailure(error -> log.error("An error occurred " + error));
    }

    private boolean getGraphicalArgument(final String[] args) {
        return Try.of(() -> args[1].equals("--graphical"))
                .getOrElse(Boolean.FALSE);
    }

    /**
     * Process the input by turning the mower based on the movements stored in the movements variable and executing the
     * action, displaying the output to the console, and if the graphical argument is true, then it also launches a
     * swing GUI that simulates the problem's behavior.
     *
     * @param lines     the commands to moveForward
     * @param graphical whether to display a graphical simulation or a simple console output
     * @return a {@link List} object containing the final position of the mowers
     * @see Drawer
     */
    public static List<String> processInput(final List<String> lines, final boolean graphical) {
        final var size = lines.get(0).split(SPACE);
        maxSizeX = Integer.parseInt(size[0]);
        maxSizeY = Integer.parseInt(size[1]);
        Drawer drawer = null;
        if (graphical) {
            drawer = new Drawer(maxSizeX + 1, maxSizeY + 1);
            drawer.setVisible(true);
        }
        final var results = new ArrayList<String>();
        for (int i = 1; i < lines.size(); i += 2) {
            final var position = lines.get(i).split(SPACE);
            final var movements = lines.get(i + 1);
            try {
                final Mower mower;
                if (graphical) {
                    mower = initGraphicMower(drawer, position);
                    drawer.setMower(mower);
                } else {
                    mower = initConsoleMower(position);
                }
                for (char action : movements.toCharArray()) {
                    mower.performAction(Action.getActionByAbbreviation(String.valueOf(action)));
                    if (graphical) {
                        delay(2000);
                    }
                }
                results.add(mower.toString());
            } catch (InvalidActionException e) {
                log.error(e.getMessage());
            }
        }
        return results;
    }

    private static Mower initGraphicMower(Drawer drawer, String[] position) throws InvalidActionException {
        return new Mower(new Position(position[0], position[1]),
                Orientation.getOrientationByAbbreviation(position[2]), drawer, drawer);
    }

    private static Mower initConsoleMower(String[] position) throws InvalidActionException {
        return new Mower(new Position(position[0], position[1]),
                Orientation.getOrientationByAbbreviation(position[2]));
    }

    private static void delay(int milliSeconds) {
        Unchecked.runnable(() -> Thread.sleep(milliSeconds)).run();
    }
}
