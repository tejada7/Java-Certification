package com.oca.interviewquestions.tondeuse.business.enums;

import com.oca.interviewquestions.tondeuse.exceptions.InvalidActionException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Represent the 3 lawnmower's actions:
 * TURN_RIGHT -> turn 90 degrees clockwise
 * TURN_LEFT -> turn 90 degrees anti-clockwise
 * MOVE_FORWARD -> move the mower forward based on the current {@link Orientation}
 */
public enum Actions {
    TURN_RIGHT("D"), TURN_LEFT("G"), MOVE_FORWARD("A");

    private String abbreviation;

    private static Map<String, Actions> actionsByAbbreviation = new HashMap<>();

    static {
        Arrays.stream(Actions.values()).forEach(item -> actionsByAbbreviation.put(item.abbreviation, item));
    }

    Actions(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    /**
     * Retrieve the enum constant given its abbreviation.
     *
     * @param abbreviation a {@link String} abbreviation representing the action
     * @return a {@link Orientation} constant
     * @throws InvalidActionException if no abbreviation matches to the stored ones
     */
    public static Actions getActionByAbbreviation(String abbreviation) throws InvalidActionException {
        return Optional.of(actionsByAbbreviation.get(abbreviation.toUpperCase())).orElseThrow(
                () -> new InvalidActionException("The option " + abbreviation + " is invalid.")
        );
    }
}
