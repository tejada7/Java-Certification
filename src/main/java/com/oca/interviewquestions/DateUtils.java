package com.oca.interviewquestions;

import java.time.LocalDate;
import java.util.Date;

/**
 * Contain helpful methods for dates.
 */
public class DateUtils {

    private DateUtils() {
        throw new IllegalStateException("Constructor not allowed for a util class.");
    }

    /**
     * A leap year occurs every for years, and the rules to determine whether a year is catalogued under such category
     * are:
     * - The year must be divisible by 4
     * - The year mustn't be divisible by 100 but 400
     *
     * @param year the year to eb evaluated
     * @return <code>true<code/> or <code>false<code/> according to the evaluation
     */
    public static boolean isLeapYear(int year) {
        return ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0));
    }

    /**
     * @param localDate the year contained in a {@link LocalDate} object
     * @return <code>true<code/> or <code>false<code/> according to the evaluation
     * @see {@link DateUtils#isLeapYear(int)}
     */
    public static boolean isLeapYear(LocalDate localDate) {
        return isLeapYear(localDate.getYear());
    }

    /**
     * @param date the year contained in a {@link Date} object
     * @return <code>true<code/> or <code>false<code/> according to the evaluation
     * @see {@link DateUtils#isLeapYear(int)}
     */
    public static boolean isLeapYear(Date date) {
        return isLeapYear(date.getYear());
    }
}
