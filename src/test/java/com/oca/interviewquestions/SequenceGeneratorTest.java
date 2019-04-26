package com.oca.interviewquestions;

import org.junit.Test;

import static com.oca.interviewquestions.SequenceGenerator.recursiveFibonacci;
import static org.junit.Assert.assertEquals;

public class SequenceGeneratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void fibonacciOutOfScope() {
        recursiveFibonacci(0);
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
    }
}