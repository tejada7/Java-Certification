package com.oca.interviewquestions.tondeuse;

import com.oca.interviewquestions.tondeuse.business.Mower;
import com.oca.interviewquestions.tondeuse.business.enums.Orientation;
import com.oca.interviewquestions.tondeuse.exceptions.InvalidActionException;
import com.oca.interviewquestions.tondeuse.model.Position;
import com.oca.interviewquestions.tondeuse.view.Drawer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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

    public static List<String> processInput(List<String> lines, boolean animation) {
        List<String> results = new ArrayList<>();
        final String[] size = lines.get(0).split(SEPARATOR);
        Mower.setMaxSizeX(Integer.parseInt(size[0]));
        Mower.setMaxSizeY(Integer.parseInt(size[1]));
        if (animation) {
            animationExecution(lines, results);
        } else {
            consoleExecution(lines, results);
        }
        return results;
    }

    private static void animationExecution(List<String> lines, List<String> results) {
        Drawer drawer = new Drawer(Mower.getMaxSizeX(), Mower.getMaxSizeY());
        drawer.setVisible(true);
        for (int i = 1; i < lines.size(); i += 2) {
            final String[] position = lines.get(i).split(SEPARATOR);
            final String movements = lines.get(i + 1);
            try {
                final Mower mower = new Mower(new Position(position[0], position[1]),
                        Orientation.getOrientationByAbbreviation(position[2]),
                        movements);
                drawer.setMower(mower);
                mower.performActions(drawer);
            } catch (InvalidActionException e) {
                LOGGER.severe(e.getMessage());
            }
        }
    }

    public static void consoleExecution(List<String> lines, List<String> results) {
        for (int i = 1; i < lines.size(); i += 2) {
            final String[] position = lines.get(i).split(SEPARATOR);
            final String movements = lines.get(i + 1);
            try {
                results.add(new Mower(new Position(position[0], position[1]),
                        Orientation.getOrientationByAbbreviation(position[2]),
                        movements).performActions());
            } catch (InvalidActionException e) {
                LOGGER.severe(e.getMessage());
            }
        }
    }
}
