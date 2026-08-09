package com.javacertification.interviewquestions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class DoubleUtilsTest {

    @Test
    void approximatePIValue() {
        // Given
        double rands[][] = new double[100000][2];
        for (int i = 0; i < rands.length; i++) {
            rands[i][0] = Math.random();
            rands[i][1] = Math.random();
        }
        // When
        final double pi = DoubleUtils.approximationPI(rands);

        // Then
        then(pi).isBetween(3.13, 3.15);
    }
}
