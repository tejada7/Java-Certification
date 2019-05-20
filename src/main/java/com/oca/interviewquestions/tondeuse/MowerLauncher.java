package com.oca.interviewquestions.tondeuse;

import com.oca.interviewquestions.tondeuse.business.enums.Action;
import com.oca.interviewquestions.tondeuse.business.enums.Orientation;
import com.oca.interviewquestions.tondeuse.exceptions.InvalidActionException;
import com.oca.interviewquestions.tondeuse.model.Mower;
import com.oca.interviewquestions.tondeuse.model.Position;
import com.oca.interviewquestions.tondeuse.view.Drawer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.oca.interviewquestions.tondeuse.model.Field.maxSizeX;
import static com.oca.interviewquestions.tondeuse.model.Field.maxSizeY;

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
     * Process the input either by using the {@link MowerLauncher#consoleExecution(List)} or the
     * {@link MowerLauncher#animationExecution(List)} based on the boolean value animation.
     *
     * @param lines     the commands to execute
     * @param animation whether to display a graphical simulation or a simpel console output
     * @return a {@link List} object containing the final position of the mowers
     */
    public static List<String> processInput(List<String> lines, boolean animation) {
        final String[] size = lines.get(0).split(SEPARATOR);
        maxSizeX = Integer.parseInt(size[0]);
        maxSizeY = Integer.parseInt(size[1]);
        return animation ? animationExecution(lines) : consoleExecution(lines);
    }

    /**
     * Launch a swing GUI to simulate the problem's behavior. Turn the mower based on the movements stored in the
     * movements variable and executes the action, displaying the output to the console.
     */
    private static List<String> animationExecution(List<String> lines) {
        Drawer drawer = new Drawer(maxSizeX, maxSizeY);
        drawer.setVisible(true);

        List<String> results = new ArrayList<>();
        for (int i = 1; i < lines.size(); i += 2) {
            final String[] position = lines.get(i).split(SEPARATOR);
            final String movements = lines.get(i + 1);
            try {
                final Mower mower = new Mower(new Position(position[0], position[1]),
                        Orientation.getOrientationByAbbreviation(position[2]), drawer, drawer);
                drawer.setMower(mower);
                for (char action : movements.toCharArray()) {
                    mower.performAction(Action.getActionByAbbreviation(String.valueOf(action)));
                    delay(3000);
                }
                results.add(mower.toString());
            } catch (InvalidActionException e) {
                LOGGER.severe(e.getMessage());
            }
        }
        return results;
    }

    /**
     * Turn the mower based on the movements stored in the movements variable and executes the action, displaying the
     * output to the console.
     */
    private static List<String> consoleExecution(List<String> lines) {
        List<String> results = new ArrayList<>();
        for (int i = 1; i < lines.size(); i += 2) {
            final String[] position = lines.get(i).split(SEPARATOR);
            final String movements = lines.get(i + 1);
            try {
                final Mower mower = new Mower(new Position(position[0], position[1]),
                        Orientation.getOrientationByAbbreviation(position[2]));
                for (char action : movements.toCharArray()) {
                    mower.performAction(Action.getActionByAbbreviation(String.valueOf(action)));
                }
                results.add(mower.toString());
            } catch (InvalidActionException e) {
                LOGGER.severe(e.getMessage());
            }
        }
        return results;
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