package com.javacertification.interviewquestions;

import java.math.BigInteger;

/**
 * Util containing mathematical generations.
 */
public class SequenceGenerator {

    private SequenceGenerator() {
        throw new IllegalStateException("This class is not to be constructed.");
    }

    /**
     * Get the sum of the nth fibonacci element(s).
     *
     * @param n the position
     * @return a <code>int<code/> value
     * @throws IllegalArgumentException if the argument is less than 1
     */
    public static int recursiveFibonacci(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("The argument must be greater than 0");
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return (recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2));
    }

    /**
     * Get the sum of the nth fibonacci element(s).
     *
     * @param n the position
     * @return a <code>int<code/> value
     * @throws IllegalArgumentException if the argument is less than 1
     */
    public static int iterativeFibonacci(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("The argument must be greater than 0");
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int c3 = 0;
        for (int i = 0, c = 1, c1 = 1; i < n - 2; i++, c = c1, c1 = c3) {
            c3 = c + c1;
        }
        return c3;
    }

    /**
     * Optimization of the {@link SequenceGenerator#recursiveFibonacci} implementation, inspired from the book Clean
     * Craftmanship.
     *
     * @param n the nth element of the fibonacci series
     * @return the element
     */
    public static BigInteger tailRecursiveFibonacci(final int n) {
        if (n < 1) {
            throw new IllegalArgumentException("The argument must be greater than 0");
        }
        return tailRecursive(BigInteger.ZERO, BigInteger.ONE, n);
    }

    private static BigInteger tailRecursive(final BigInteger prev, final BigInteger current, final int remainingIteration) {
        return remainingIteration > 1
                ? tailRecursive(current, current.add(prev), remainingIteration - 1)
                : current;
    }
}
