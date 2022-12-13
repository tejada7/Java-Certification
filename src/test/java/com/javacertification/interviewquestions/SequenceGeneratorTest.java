package com.javacertification.interviewquestions;

import org.junit.Test;

import java.math.BigInteger;

import static com.javacertification.interviewquestions.SequenceGenerator.*;
import static org.junit.Assert.assertEquals;

public class SequenceGeneratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void fibonacciOutOfScope() {
        recursiveFibonacci(0);
        iterativeFibonacci(0);
        tailRecursiveFibonacci(0);
    }

    @Test
    public void fibonacci() {
        assertEquals(1, recursiveFibonacci(1));
        assertEquals(1, recursiveFibonacci(2));
        assertEquals(2, recursiveFibonacci(3));
        assertEquals(3, recursiveFibonacci(4));
        assertEquals(5, recursiveFibonacci(5));
        assertEquals(8, recursiveFibonacci(6));
        assertEquals(13, recursiveFibonacci(7));

        assertEquals(1, iterativeFibonacci(1));
        assertEquals(1, iterativeFibonacci(2));
        assertEquals(2, iterativeFibonacci(3));
        assertEquals(3, iterativeFibonacci(4));
        assertEquals(5, iterativeFibonacci(5));
        assertEquals(8, iterativeFibonacci(6));
        assertEquals(13, iterativeFibonacci(7));

        assertEquals(BigInteger.valueOf(1), tailRecursiveFibonacci(1));
        assertEquals(BigInteger.valueOf(1), tailRecursiveFibonacci(2));
        assertEquals(BigInteger.valueOf(2), tailRecursiveFibonacci(3));
        assertEquals(BigInteger.valueOf(3), tailRecursiveFibonacci(4));
        assertEquals(BigInteger.valueOf(5), tailRecursiveFibonacci(5));
        assertEquals(BigInteger.valueOf(8), tailRecursiveFibonacci(6));
        assertEquals(BigInteger.valueOf(13), tailRecursiveFibonacci(7));
    }
}
