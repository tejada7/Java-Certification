package com.oca.interviewQuestions;

/**
 * Contain helpful methods for dates.
 */
public class DateUtils {

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
}
