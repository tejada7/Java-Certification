package com.oca.interviewquestions.tondeuse.business.enums;

import com.oca.interviewquestions.tondeuse.exceptions.InvalidActionException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Represent the 4 cardinal positions of the lawnmower, along with the business logic when turning in different
 * directions.
 */
public enum Orientation {
    NORTH("N") {
        @Override
        Orientation turnRight() {
            return EAST;
        }

        @Override
        Orientation turnLeft() {
            return WEST;
        }
    }, SOUTH("S") {
        @Override
        Orientation turnRight() {
            return WEST;
        }

        @Override
        Orientation turnLeft() {
            return EAST;
        }
    }, WEST("W") {
        @Override
        Orientation turnRight() {
            return NORTH;
        }

        @Override
        Orientation turnLeft() {
            return SOUTH;
        }
    }, EAST("E") {
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
    private static Map<String, Orientation> positionByAbbreviation = new HashMap<>();

    static {
        Arrays.stream(Orientation.values()).forEach(item -> positionByAbbreviation.put(item.abbreviation, item));
    }

    Orientation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public Orientation turn(Actions actions, Consumer<Orientation> consumer) {
        switch (actions) {
            case TURN_RIGHT:
                return turnRight();
            case TURN_LEFT:
                return turnLeft();
            default:
                consumer.accept(this);
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
}
