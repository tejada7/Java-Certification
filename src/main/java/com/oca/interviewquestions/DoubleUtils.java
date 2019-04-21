package com.oca.interviewquestions;

import java.math.BigDecimal;

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
}
