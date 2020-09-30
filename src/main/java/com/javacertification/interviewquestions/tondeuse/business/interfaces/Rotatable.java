package com.javacertification.interviewquestions.tondeuse.business.interfaces;

import com.javacertification.interviewquestions.tondeuse.business.enums.Orientation;

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
