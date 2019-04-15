package com.oca.interviewQuestions;

import org.junit.Assert;
import org.junit.Test;

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
}
