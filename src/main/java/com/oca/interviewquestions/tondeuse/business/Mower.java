package com.oca.interviewquestions.tondeuse.business;

import com.oca.interviewquestions.tondeuse.MowerLauncher;
import com.oca.interviewquestions.tondeuse.business.enums.Actions;
import com.oca.interviewquestions.tondeuse.business.enums.Orientation;
import com.oca.interviewquestions.tondeuse.exceptions.InvalidActionException;
import com.oca.interviewquestions.tondeuse.model.Position;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contain the business logic of the mower problem described in {@link MowerLauncher}.
 */
public class Mower {

    private static final Logger LOGGER = Logger.getLogger(Mower.class.toString());

    private static int maxSizeX;
    private static int maxSizeY;
    private Position currentPosition;
    private String movements;
    private Orientation orientation;

    public Mower(Position currentPosition, Orientation orientation, String movements) {
        this.currentPosition = currentPosition;
        this.orientation = orientation;
        this.movements = movements;
    }

    public static void setMaxSizeX(int maxSizeX) {
        Mower.maxSizeX = maxSizeX;
    }

    public static void setMaxSizeY(int maxSizeY) {
        Mower.maxSizeY = maxSizeY;
    }

    /**
     * Turn the mower based on the movements stored in {@link Mower#movements}.
     *
     * @return a {@link String} object containing the position and the orientation, all separated by space e.g. 1 2 E,
     * representing position 1 in x, position 2 in y and east direction
     * @throws InvalidActionException if an invalid action is found
     */
    public String performActions() throws InvalidActionException {
        for (char action : movements.toCharArray()) {
            orientation = orientation.turn(Actions.getActionByAbbreviation(String.valueOf(action)),
                    this::moveForward);
        }
        return currentPosition + " " + orientation.getAbbreviation();
    }

    private void moveForward(Orientation orientation) {
        LOGGER.log(Level.INFO, "Moving from {0} in {1} direction", new Object[]{currentPosition, orientation});
        switch (orientation) {
            case NORTH:
                if (currentPosition.posY + 1 < maxSizeY) {
                    currentPosition.posY++;
                }
                break;
            case SOUTH:
                if (currentPosition.posY - 1 >= 0) {
                    currentPosition.posY--;
                }
                break;
            case WEST:
                if (currentPosition.posX - 1 >= 0) {
                    currentPosition.posX--;
                }
                break;
            case EAST:
                if (currentPosition.posX + 1 < maxSizeX) {
                    currentPosition.posX++;
                }
                break;
            default:
                //DO NOTHING
        }
        LOGGER.log(Level.INFO, "The new Position is {0}", currentPosition);
    }
}
