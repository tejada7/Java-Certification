package com.javacertification.interviewquestions.tondeuse.business.interfaces;

import com.javacertification.interviewquestions.tondeuse.business.enums.Orientation;

/**
 * Represent the action to perform when calling the operation move forward.
 */
@FunctionalInterface
public interface Movable {

    /**
     * Implement the functionality of the action.
     *
     * @param orientation the current orientation
     */
    void moveForward(Orientation orientation);
}
