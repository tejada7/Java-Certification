package com.oca.interviewQuestions;

public class ReverseText {

    /**
     * Reverse a text.
     *
     * @param input
     *
     * @return the text reversed, if the input is null then the return is an empty string
     */
    public static String reverse(String input) {
        if (null == input) {
            return "";
        }
        char[] array = input.toCharArray();
        int halfLength = array.length / 2;
        char clipboard;

        for (int i = 0; i < halfLength; i++) {
            clipboard = array[i];
            final int indexLast = array.length - 1 - i;
            array[i] = array[indexLast];
            array[indexLast] = clipboard;
        }
        return String.valueOf(array);
    }
}
