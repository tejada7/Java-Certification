package com.javacertification.interviewquestions.tondeuse.business.enums;

import com.javacertification.interviewquestions.tondeuse.exceptions.InvalidActionException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Represent the 3 lawnmower's actions:
 * TURN_RIGHT -> performAction 90 degrees clockwise
 * TURN_LEFT -> performAction 90 degrees anti-clockwise
 * MOVE -> move the mower forward based on the current {@link Orientation}
 */
public enum Action {
    TURN_RIGHT("D"), TURN_LEFT("G"), MOVE("A");

    private String abbreviation;

    private static Map<String, Action> actionsByAbbreviation = new HashMap<>();

    static {
        Arrays.stream(Action.values()).forEach(item -> actionsByAbbreviation.put(item.abbreviation, item));
    }

    Action(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    /**
     * Retrieve the enum constant given its abbreviation.
     *
     * @param abbreviation a {@link String} abbreviation representing the action
     * @return a {@link Orientation} constant
     * @throws InvalidActionException if no abbreviation matches to the stored ones
     */
    public static Action getActionByAbbreviation(String abbreviation) throws InvalidActionException {
        return Optional.of(actionsByAbbreviation.get(abbreviation.toUpperCase())).orElseThrow(
                () -> new InvalidActionException("The option " + abbreviation + " is invalid.")
        );
    }
}
