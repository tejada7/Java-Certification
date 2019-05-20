package com.oca.interviewquestions.tondeuse.business.enums;

import com.oca.interviewquestions.tondeuse.business.interfaces.Movable;
import com.oca.interviewquestions.tondeuse.business.interfaces.Rotatable;
import com.oca.interviewquestions.tondeuse.exceptions.InvalidActionException;
import com.oca.interviewquestions.tondeuse.view.Drawer;
import com.oca.interviewquestions.tondeuse.view.TextAreaHandler;

import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represent the 4 cardinal positions of the lawnmower, along with the business logic when turning in different
 * directions.
 */
public enum Orientation implements Rotatable {
    NORTH("N", "mowerN.png") {
        @Override
        public Orientation turnRight() {
            return EAST;
        }

        @Override
        public Orientation turnLeft() {
            return WEST;
        }
    }, SOUTH("S", "mowerS.png") {
        @Override
        public Orientation turnRight() {
            return WEST;
        }

        @Override
        public Orientation turnLeft() {
            return EAST;
        }
    }, WEST("W", "mowerW.png") {
        @Override
        public Orientation turnRight() {
            return NORTH;
        }

        @Override
        public Orientation turnLeft() {
            return SOUTH;
        }
    }, EAST("E", "mowerE.png") {
        @Override
        public Orientation turnRight() {
            return SOUTH;
        }

        @Override
        public Orientation turnLeft() {
            return NORTH;
        }
    };

    private String abbreviation;
    private String imgPath;
    private static Map<String, Orientation> positionByAbbreviation = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(Orientation.class.toString());

    static {
        LOGGER.addHandler(new TextAreaHandler(Drawer.textArea));
        Arrays.stream(Orientation.values()).forEach(item -> positionByAbbreviation.put(item.abbreviation, item));
    }

    Orientation(String abbreviation, String imgPath) {
        this.abbreviation = abbreviation;
        this.imgPath = imgPath;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * Return the new orientation based on a given action. It additionally executes the implementation of a
     * {@link Movable} interface.
     *
     * @param action       the type of action
     * @param movablesImpl the functions to be executed for a {@link Movable} implementation
     * @return the new orientation based on the rules depicted in the methods {@link Orientation#turnRight()} and
     * {@link Orientation#turnLeft()}
     */
    public Orientation performActionBasedOnOrientation(Action action, List<Movable> movablesImpl) {
        Orientation newOrientation = this;
        switch (action) {
            case TURN_RIGHT:
                newOrientation = turnRight();
                LOGGER.log(Level.INFO, "Rotating to the " + newOrientation);
                break;
            case TURN_LEFT:
                newOrientation = turnLeft();
                LOGGER.log(Level.INFO, "Rotating to the " + newOrientation);
                break;
            case MOVE:
                movablesImpl.forEach(strategy -> strategy.execute(this));
                break;
            default:
                // DO NOTHING
        }
        return newOrientation;
    }

    /**
     * Return the new orientation based on a given action. It additionally executes the implementation of a
     * {@link Rotatable} interface.
     *
     * @param action        the type of action
     * @param rotatableImpl the functions to be executed for a {@link Rotatable} implementation
     * @param movablesImpl  the functions to be executed for a {@link Movable} implementation
     * @return the new orientation based on the rules depicted in the methods {@link Orientation#turnRight()} and
     * {@link Orientation#turnLeft()}
     */
    public Orientation performActionBasedOnOrientation(Action action, Rotatable rotatableImpl, List<Movable> movablesImpl) {
        switch (action) {
            case TURN_RIGHT:
                rotatableImpl.turnRight();
                break;
            case TURN_LEFT:
                rotatableImpl.turnLeft();
                break;
            default:
                // DO NOTHING
        }
        return performActionBasedOnOrientation(action, movablesImpl);
    }

    /**
     * Retrieve the enum constant given its abbreviation.
     *
     * @param abbreviation a {@link String} abbreviation representing the orientation
     * @return a {@link Orientation} constant
     * @throws InvalidActionException if no abbreviation matches to the stored ones
     */
    public static Orientation getOrientationByAbbreviation(String abbreviation) throws InvalidActionException {
        return Optional.of(positionByAbbreviation.get(abbreviation.toUpperCase())).orElseThrow(
                () -> new InvalidActionException("The action " + abbreviation + " is invalid.")
        );
    }

    /**
     * Return the URL containing the image representing orientation.
     *
     * @return an {@link URL} object
     */
    public URL getImageURL() {
        return Objects.requireNonNull(this.getClass().getClassLoader().getResource(imgPath));
    }
}
