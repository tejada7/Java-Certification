package com.javacertification.interviewquestions;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateUtilsTest {

    @Test
    public void isLeapYearTest() {
        assertTrue(DateUtils.isLeapYear(1992));
        assertFalse(DateUtils.isLeapYear(2001));
        assertTrue(DateUtils.isLeapYear(1996));
        assertFalse(DateUtils.isLeapYear(2005));
    }
}