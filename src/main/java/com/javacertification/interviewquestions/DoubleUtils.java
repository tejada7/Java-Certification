package com.javacertification.interviewquestions;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Contain helpful methods for double numbers.
 */
public class DoubleUtils {

    private DoubleUtils() {
        throw new IllegalStateException("Constructor not allowed for a util class.");
    }

    /**
     * Get the difference between two numbers and returns its square.
     *
     * @param n1 the first number
     * @param n2 the second number
     * @return a <code>double</code> value containing the square of the difference between the parameters
     */
    public static double calculateDiffSquare(double n1, double n2) {
        return Math.pow(n1 - n2, 2);
    }

    /**
     * Round a double value to a desired number of significant decimals
     *
     * @param value the original value
     * @param numberOfDecimals the number of significant decimals to round to
     * @return a <code>double</code> value containing a numberOfDecimals decimals
     */
    public static double roundToNDecimals(double value, int numberOfDecimals) {
        return BigDecimal.valueOf(value).setScale(numberOfDecimals, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * Approximate PI using the given points
     *
     * @param points a bunch of points in the cartesian plane
     * @return a <code>double<code/> approximation of the value PI.
     */
    public static double approximationPI(double[][] points) {
        if (Objects.isNull(points)) {
            throw new IllegalArgumentException("The input cannot be null.");
        }
        double chances = 0;
        for (int i = 0; i < points.length; i++) {
            double x = points[i][0];
            double y = points[i][1];
            final double sumSquares = Math.pow(x, 2) + Math.pow(y, 2);
            if (sumSquares  <= 1) {
                chances ++;
            }
        }
        return 4 * (chances / points.length);
    }
}
