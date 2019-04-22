package com.oca.interviewquestions;

import com.oca.interviewquestions.model.Fruit;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.oca.interviewquestions.IntUtils.*;
import static com.oca.interviewquestions.model.Fruit.*;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.valueOf;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class IntUtilsTest {

    private static void execute() {
        factorial(-1);
    }

    @Test
    public void isPowerOfTest() {
        assertTrue(isPowerOf(4, 2));

        assertTrue(isPowerOf(27, 3));
        assertFalse(isPowerOf(124, 5));
    }

    @Test
    public void isPalindromeTest() {
        assertTrue(isPalindrome(12321));
        assertFalse(isPalindrome(123));
    }

    @Test
    public void isArmstrongTest() {
        assertTrue(isArmstrong(153));
        assertTrue(isArmstrong(370));
        assertTrue(isArmstrong(1634));
        assertTrue(isArmstrong(9474));
        assertTrue(isArmstrong(1));
        assertFalse(isArmstrong(1000));
    }

    @Test
    public void isHappyTest() {
        assertTrue(isHappy(23));
        assertTrue(isHappy(193));
        assertTrue(isHappy(761));
        assertFalse(isHappy(4));
        assertFalse(isHappy(737));
    }

    @Test
    public void isPrimeTest() {
        assertTrue(isPrime(7));
        assertFalse(isPrime(12));
    }

    @Test
    public void pairCombinationNumberTest() {
        assertEquals(6, getFruitPairCombinationsNumber(new HashSet<>(
                asList(APPLE, BANANA, ORANGE, PEAR))));
    }

    @Test
    public void pairCombinationElementsTest() {
        final List<Set<Fruit>> result = getFruitPairCombinationsElements(new ArrayList<>(
                asList(APPLE, BANANA, ORANGE, PEAR)));
        assertEquals(6, result.size());
        assertTrue(result.contains(of(APPLE, BANANA)));
        assertTrue(result.contains(of(APPLE, ORANGE)));
        assertTrue(result.contains(of(APPLE, PEAR)));
        assertTrue(result.contains(of(BANANA, ORANGE)));
        assertTrue(result.contains(of(BANANA, PEAR)));
        assertTrue(result.contains(of(ORANGE, PEAR)));
    }

    @Test
    public void factorialTest() {
        assertEquals(ONE, factorial(0));
        assertEquals(ONE, factorial(1));
        assertEquals(valueOf(120), factorial(5));
        assertEquals(valueOf(40320), factorial(8));
        Assertions.assertThrows(IllegalArgumentException.class, IntUtilsTest::execute);
    }
}
