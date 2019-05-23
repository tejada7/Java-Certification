package com.oca.interviewquestions.tondeuse.business.interfaces;

import com.oca.interviewquestions.tondeuse.business.enums.Orientation;

/**
 * Define the behavior of a rotable element.
 */
public interface Rotatable {
    /**
     * Rotate an orientation 90 degrees anti-clockwise.
     *
     * @return an {@link Orientation} constant
     */
    Orientation turnRight();

    /**
     * Rotate an orientation 90 degrees clockwise.
     *
     * @return an {@link Orientation} constant
     */
    Orientation turnLeft();
}
