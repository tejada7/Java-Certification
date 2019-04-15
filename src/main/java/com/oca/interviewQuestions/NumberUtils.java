package com.oca.interviewQuestions;

/**
 * Contain helpful methods for int numbers.
 */
public class NumberUtils {

    /**
     * Determines if a number is a power of another number.
     *
     * @param number  the value to be raised to
     * @param powerOf the power
     * @return <code>true<code/> or <code>false<code/> according to the evaluation
     */
    static boolean isPowerOf(int number, int powerOf) {
        return Math.pow(number, 1d / powerOf) % 1 == 0d;
    }

    /**
     * Determines is a number is a palindrome, e.g. 1001, 14541
     *
     * @param value the number to evaluate
     * @return <code>true<code/> or <code>false<code/> according to the evaluation
     */
    static boolean isPalindrome(int value) {
        do {
            int lastDigit = value % 10;
            int firstDigit = value / (int) Math.pow(10, (int) Math.log10(value));
            if (lastDigit != firstDigit) {
                return false;
            }
            value /= 10;
            if (value == 0) {
                break;
            }
            value %= (int) Math.pow(10, (int) Math.log10(value));
        } while (value > 0);
        return true;
    }

    /**
     * Determines whether a number is Armstrong number. An Armstrong number is a number whose value is
     * equal to the sum of its digits raised to the power of its length e.g. 153 because 1^3 + 5^3 + 3^3 = 153.
     *
     * @param value the number to evaluate
     * @return <code>true<code/> or <code>false<code/> according to the evaluation
     */
    static boolean isArmstrong(int value) {
        int exponent = (int) Math.log10(value) + 1;
        int sum = 0;
        int originalValue = value;
        do {
            sum += (int) Math.pow(value % 10, exponent);
            value /= 10;
        } while (value > 0);
        return originalValue == sum;
    }
}
