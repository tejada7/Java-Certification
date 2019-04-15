package com.oca.interviewQuestions;

import com.oca.interviewQuestions.model.Fruit;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class NumberUtilsTest {

    @Test
    public void isPowerOfTwoTest() {

        Assert.assertTrue(NumberUtils.isPowerOf(4, 2));

        Assert.assertTrue(NumberUtils.isPowerOf(27, 3));
        Assert.assertFalse(NumberUtils.isPowerOf(124, 5));
    }

    @Test
    public void isPalindromeTest() {
        Assert.assertTrue(NumberUtils.isPalindrome(12321));
        Assert.assertFalse(NumberUtils.isPalindrome(123));
    }

    @Test
    public void isArmstrongTest() {
        Assert.assertTrue(NumberUtils.isArmstrong(153));
        Assert.assertTrue(NumberUtils.isArmstrong(370));
        Assert.assertTrue(NumberUtils.isArmstrong(1634));
        Assert.assertTrue(NumberUtils.isArmstrong(9474));
        Assert.assertTrue(NumberUtils.isArmstrong(1));
        Assert.assertFalse(NumberUtils.isArmstrong(1000));
    }

    @Test
    public void isHappyTest() {
        Assert.assertTrue(NumberUtils.isHappy(23));
        Assert.assertTrue(NumberUtils.isHappy(193));
        Assert.assertTrue(NumberUtils.isHappy(761));
        Assert.assertFalse(NumberUtils.isHappy(4));
        Assert.assertFalse(NumberUtils.isHappy(737));
    }

    @Test
    public void isPrimeTest() {
        Assert.assertTrue(NumberUtils.isPrime(7));
        Assert.assertFalse(NumberUtils.isPrime(12));
    }

    @Test
    public void pairCombinationNumberTest() {
        Assert.assertEquals(6, NumberUtils.getPairCombinationsNumber(new HashSet<>(
                Arrays.asList(Fruit.APPLE,
                        Fruit.BANANA,
                        Fruit.ORANGE,
                        Fruit.PEAR))));
    }

    @Test
    public void pairCombinationElementsTest() {
        final List<Set<Fruit>> result = NumberUtils.getPairCombinationsElements(new ArrayList<>(
                Arrays.asList(Fruit.APPLE,
                        Fruit.BANANA,
                        Fruit.ORANGE,
                        Fruit.PEAR)));
        Assert.assertEquals(6, result.size());
        Assert.assertTrue(result.contains(Fruit.of(Fruit.APPLE, Fruit.BANANA)));
        Assert.assertTrue(result.contains(Fruit.of(Fruit.APPLE, Fruit.ORANGE)));
        Assert.assertTrue(result.contains(Fruit.of(Fruit.APPLE, Fruit.PEAR)));
        Assert.assertTrue(result.contains(Fruit.of(Fruit.BANANA, Fruit.ORANGE)));
        Assert.assertTrue(result.contains(Fruit.of(Fruit.BANANA, Fruit.PEAR)));
        Assert.assertTrue(result.contains(Fruit.of(Fruit.ORANGE, Fruit.PEAR)));
    }
}
