package com.oca.interviewquestions;

import java.util.*;

/**
 * Contain helpful methods for int numbers.
 */
public class StringUtils {

    private StringUtils() {
        throw new IllegalStateException("Constructor not allowed for a util class.");
    }

    private static final String EMPTY_STRING = "";

    /**
     * Recursive method that returns all possible permutations of any {@link String} object.
     *
     * @param text the input text
     * @return a list of {@link String} objects
     */
    public static List<String> recursivePermutation(String text) {
        return permutation(EMPTY_STRING, text);
    }

    private static List<String> permutation(String perm, String text) {
        List<String> result = new ArrayList<>();
        if (null != text && !text.isEmpty()) {
            int length = text.length();
            for (int i = 0; i < length; i++) {
                String perm2 = perm + text.charAt(i);
                String text2 = text.substring(0, i) + text.substring(i + 1, length);
                result.addAll(permutation(perm2, text2));
            }
        } else {
            result.add(perm);
        }
        return result;
    }

    /**
     * Itarative method that returns all possible permutations of any {@link String} object.
     *
     * @param text the input text
     * @return a list of {@link String} objects
     */
    public static List<String> iterativePermutation(String text) {
        List<String> permutations = new ArrayList<>();
        List<String> result = new ArrayList<>();
        result.add(text.substring(0, 1));
        int length = text.length();
        for (int i = 1; i < length; i++) {
            permutations.clear();
            for (String perm : result) {
                permutations.addAll(addCharacter(text.charAt(i), perm));
            }
            result.clear();
            result.addAll(permutations);
        }
        return result;
    }

    private static Collection<? extends String> addCharacter(char c, String text) {
        Set<String> set = new HashSet<>();
        int length = text.length();
        for (int i = 0; i <= length; i++) {
            set.add(text.substring(0, i) + c + text.substring(i, length));
        }
        return set;
    }

    /**
     * Reverse a text.
     *
     * @param input the text to be reversed
     * @return the text reversed, if the input is null then the return is an empty string
     */
    public static String reverse(String input) {
        if (null == input) {
            return EMPTY_STRING;
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
