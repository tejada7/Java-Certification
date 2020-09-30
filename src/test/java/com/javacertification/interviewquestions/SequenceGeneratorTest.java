package com.javacertification.interviewquestions;

import org.junit.Test;

import static com.javacertification.interviewquestions.SequenceGenerator.iterativeFibonacci;
import static com.javacertification.interviewquestions.SequenceGenerator.recursiveFibonacci;
import static org.junit.Assert.assertEquals;

public class SequenceGeneratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void fibonacciOutOfScope() {
        recursiveFibonacci(0);
        iterativeFibonacci(0);
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
    }
}