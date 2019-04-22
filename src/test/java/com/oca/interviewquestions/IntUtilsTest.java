package com.oca.interviewquestions;

import com.oca.interviewquestions.model.Fruit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigInteger;
import java.util.*;

public class IntUtilsTest {

    private static void execute() {
        IntUtils.factorial(-1);
    }

    @Test
    public void isPowerOfTest() {
        Assert.assertTrue(IntUtils.isPowerOf(4, 2));

        Assert.assertTrue(IntUtils.isPowerOf(27, 3));
        Assert.assertFalse(IntUtils.isPowerOf(124, 5));
    }

    @Test
    public void isPalindromeTest() {
        Assert.assertTrue(IntUtils.isPalindrome(12321));
        Assert.assertFalse(IntUtils.isPalindrome(123));
    }

    @Test
    public void isArmstrongTest() {
        Assert.assertTrue(IntUtils.isArmstrong(153));
        Assert.assertTrue(IntUtils.isArmstrong(370));
        Assert.assertTrue(IntUtils.isArmstrong(1634));
        Assert.assertTrue(IntUtils.isArmstrong(9474));
        Assert.assertTrue(IntUtils.isArmstrong(1));
        Assert.assertFalse(IntUtils.isArmstrong(1000));
    }

    @Test
    public void isHappyTest() {
        Assert.assertTrue(IntUtils.isHappy(23));
        Assert.assertTrue(IntUtils.isHappy(193));
        Assert.assertTrue(IntUtils.isHappy(761));
        Assert.assertFalse(IntUtils.isHappy(4));
        Assert.assertFalse(IntUtils.isHappy(737));
    }

    @Test
    public void isPrimeTest() {
        Assert.assertTrue(IntUtils.isPrime(7));
        Assert.assertFalse(IntUtils.isPrime(12));
    }

    @Test
    public void pairCombinationNumberTest() {
        Assert.assertEquals(6, Fruit.getFruitPairCombinationsNumber(new HashSet<>(
                Arrays.asList(Fruit.APPLE,
                        Fruit.BANANA,
                        Fruit.ORANGE,
                        Fruit.PEAR))));
    }

    @Test
    public void pairCombinationElementsTest() {
        final List<Set<Fruit>> result = Fruit.getFruitPairCombinationsElements(new ArrayList<>(
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

    @Test
    public void factorialTest() {
        Assert.assertEquals(BigInteger.ONE, IntUtils.factorial(0));
        Assert.assertEquals(BigInteger.ONE, IntUtils.factorial(1));
        Assert.assertEquals(BigInteger.valueOf(120), IntUtils.factorial(5));
        Assert.assertEquals(BigInteger.valueOf(40320), IntUtils.factorial(8));
        Assertions.assertThrows(IllegalArgumentException.class, IntUtilsTest::execute);
    }
}
