package com.oca.interviewQuestions;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

    @Test
    public void isLeapYearTest() {
        Assert.assertTrue(DateUtils.isLeapYear(1992));
        Assert.assertFalse(DateUtils.isLeapYear(2001));
        Assert.assertTrue(DateUtils.isLeapYear(1996));
        Assert.assertFalse(DateUtils.isLeapYear(2005));
    }
}