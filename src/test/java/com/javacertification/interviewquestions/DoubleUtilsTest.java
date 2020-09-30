package com.javacertification.interviewquestions;

import org.junit.Assert;
import org.junit.Test;

public class DoubleUtilsTest {

    @Test
    public void approximatePIValue() {
        // Given
        double rands[][] = new double[100000][2];
        for (int i = 0; i < rands.length; i++) {
            rands[i][0] = Math.random();
            rands[i][1] = Math.random();
        }
        // When
        final double pi = DoubleUtils.approximationPI(rands);

        // Then
        Assert.assertTrue(pi < 3.15d && pi > 3.13d);
    }
}
