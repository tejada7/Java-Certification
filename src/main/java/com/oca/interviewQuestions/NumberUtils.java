package com.oca.interviewQuestions;

class NumberUtils {

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
}
