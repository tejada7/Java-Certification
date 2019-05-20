package com.oca.interviewquestions.tondeuse.business.enums;

import com.oca.interviewquestions.tondeuse.business.interfaces.Movable;
import com.oca.interviewquestions.tondeuse.exceptions.InvalidActionException;

import java.net.URL;
import java.util.*;

/**
 * Represent the 4 cardinal positions of the lawnmower, along with the business logic when turning in different
 * directions.
 */
public enum Orientation {
    NORTH("N", "mowerN.png") {
        @Override
        Orientation turnRight() {
            return EAST;
        }

        @Override
        Orientation turnLeft() {
            return WEST;
        }
    }, SOUTH("S", "mowerS.png") {
        @Override
        Orientation turnRight() {
            return WEST;
        }

        @Override
        Orientation turnLeft() {
            return EAST;
        }
    }, WEST("W", "mowerW.png") {
        @Override
        Orientation turnRight() {
            return NORTH;
        }

        @Override
        Orientation turnLeft() {
            return SOUTH;
        }
    }, EAST("E", "mowerE.png") {
        @Override
        Orientation turnRight() {
            return SOUTH;
        }

        @Override
        Orientation turnLeft() {
            return NORTH;
        }
    };

    private String abbreviation;
    private String imgPath;
    private static Map<String, Orientation> positionByAbbreviation = new HashMap<>();

    static {
        Arrays.stream(Orientation.values()).forEach(item -> positionByAbbreviation.put(item.abbreviation, item));
    }

    Orientation(String abbreviation, String imgPath) {
        this.abbreviation = abbreviation;
        this.imgPath = imgPath;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public Orientation turn(Actions actions, Movable consumer) {
        switch (actions) {
            case TURN_RIGHT:
                return turnRight();
            case TURN_LEFT:
                return turnLeft();
            default:
                consumer.moveForward(this);
                return this;
        }
    }

    abstract Orientation turnRight();

    abstract Orientation turnLeft();

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
