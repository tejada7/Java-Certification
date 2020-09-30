package com.javacertification.interviewquestions.tondeuse;

import com.javacertification.interviewquestions.tondeuse.business.enums.Action;
import com.javacertification.interviewquestions.tondeuse.business.enums.Orientation;
import com.javacertification.interviewquestions.tondeuse.exceptions.InvalidActionException;
import com.javacertification.interviewquestions.tondeuse.model.Mower;
import com.javacertification.interviewquestions.tondeuse.model.Position;
import com.javacertification.interviewquestions.tondeuse.view.Drawer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.javacertification.interviewquestions.tondeuse.model.Field.maxSizeX;
import static com.javacertification.interviewquestions.tondeuse.model.Field.maxSizeY;

/**
 * The first line corresponds to the coordinates of the upper right corner of the lawn, those
 * from the bottom left corner are assumed to be (0,0)
 * - The rest of the file allows to control all the mowers that have been deployed. Each
 * mower with two lines concerning it:
 * - the first line gives the initial position of the mower, as well as its orientation. The
 * position and orientation are provided in the form of 2 digits and a letter, separated by a space
 * - the second line is a series of instructions ordering the mower to explore the lawn.
 * The instructions are a sequence of characters without spaces.
 */
public class MowerLauncher {

    private static final Logger LOGGER = Logger.getLogger(MowerLauncher.class.toString());
    private static final String SEPARATOR = " ";

    public static void main(String[] args) {
        if (args.length == 0) {
            LOGGER.warning("No arguments are provided. Please provide the path file name containing the input.");
            return;
        }
        try {
            List<String> lines = Files.readAllLines(Paths.get(args[0]));
            processInput(lines, true).forEach(LOGGER::fine);
        } catch (IOException ioe) {
            LOGGER.severe("The " + args[0] + " has not been found.");
        }
    }

    /**
     * Process the input by turning the mower based on the movements stored in the movements variable and executing the
     * action, displaying the output to the console, and if the animation argument is true, then it also launches a
     * swing GUI that simulates the problem's behavior.
     *
     * @param lines     the commands to moveForward
     * @param animation whether to display a graphical simulation or a simpel console output
     * @return a {@link List} object containing the final position of the mowers
     * @see Drawer
     */
    public static List<String> processInput(List<String> lines, boolean animation) {
        final String[] size = lines.get(0).split(SEPARATOR);
        maxSizeX = Integer.parseInt(size[0]);
        maxSizeY = Integer.parseInt(size[1]);
        Drawer drawer = null;
        if (animation) {
            drawer = new Drawer(maxSizeX + 1, maxSizeY + 1);
            drawer.setVisible(true);
        }
        List<String> results = new ArrayList<>();
        for (int i = 1; i < lines.size(); i += 2) {
            final String[] position = lines.get(i).split(SEPARATOR);
            final String movements = lines.get(i + 1);
            try {
                final Mower mower;
                if (animation) {
                    mower = initGraphicMower(drawer, position);
                    drawer.setMower(mower);
                } else {
                    mower = initConsoleMower(position);
                }
                for (char action : movements.toCharArray()) {
                    mower.performAction(Action.getActionByAbbreviation(String.valueOf(action)));
                    if (animation) {
                        delay(3000);
                    }
                }
                results.add(mower.toString());
            } catch (InvalidActionException e) {
                LOGGER.severe(e.getMessage());
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
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
