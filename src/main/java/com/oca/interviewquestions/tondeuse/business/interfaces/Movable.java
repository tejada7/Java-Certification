package com.oca.interviewquestions.tondeuse.business.interfaces;

import com.oca.interviewquestions.tondeuse.business.enums.Orientation;

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
    void execute(Orientation orientation);
}
