package com.javacertification.interviewquestions.tondeuse.exceptions;

/**
 * Represent an invalid or non existing action.
 */
public class InvalidActionException extends Exception {

    /**
     * {@inheritDoc}
     * @param message the exception message
     */
    public InvalidActionException(String message) {
        super(message);
    }
}
