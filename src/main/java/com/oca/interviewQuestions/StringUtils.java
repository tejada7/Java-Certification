package com.oca.interviewQuestions;

import java.util.*;

/**
 * Contain helpful methods for int numbers.
 */
public class StringUtils {

    private static final String EMPTY_STRING = "";

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
     * Recursive method that returns all possible permutations of any {@link String} object.
     *
     * @param text the input text
     * @return a list of {@link String} objects
     */
    public static List<String> recursivePermutation(String text) {
        return permutation(EMPTY_STRING, text);
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
        for (int i = 0; i <= length ; i++) {
            set.add(text.substring(0, i) + c + text.substring(i, length));
        }
        return set;
    }
}
