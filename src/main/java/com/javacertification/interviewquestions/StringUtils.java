package com.javacertification.interviewquestions;

import java.util.*;
import java.util.LinkedList;

/**
 * Contain helpful methods for int numbers.
 */
public class StringUtils {

    private StringUtils() {
        throw new IllegalStateException("Constructor not allowed for a util class.");
    }

    public static final String EMPTY_STRING = "";
    public static final String SINGLE_SPACE = " ";

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

    /**
     * Capitalize the first letter of each array string element.
     *
     * @param words the arrays containing a group of words
     * @return a new {@link String} array
     */
    public static String[] capitalizeFirstLetter(String[] words) {
        return Arrays.stream(words).map(StringUtils::capitalizeFirstLetter).toArray(String[]::new);
    }

    private static String capitalizeFirstLetter(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1, word.length()).toLowerCase();
    }

    /**
     * Capitalize the last letter of each array string element.
     *
     * @param words the arrays containing a group of words
     * @return a new {@link String} array
     */
    public static String[] capitalizeLastLetter(String[] words) {
        return Arrays.stream(words).map(StringUtils::capitalizeLastLetter).toArray(String[]::new);
    }

    private static String capitalizeLastLetter(String word) {
        final int length = word.length();
        return word.substring(0, length - 1) + word.substring(length - 1, length).toUpperCase();
    }

    /**
     * Determines if the input string contains a balanced parenthesis and brackes. e.g.
     * [()] -> true
     * (()[]) -> true
     * ([)] -> false
     * (( -> false
     * [(()]) -> false
     * ([(([[(([]))]]))]) -> true
     * [](()()[[]])()[]([]) -> true
     * ([((([(([]))])))))]) -> false
     * [](()()[[]])[][[([]) -> false
     *
     * @param str the input
     * @return a boolean value
     */
    public static boolean isBalanced(String str) {

        // set matching pairs
        Map<Character, Character> braces = new HashMap<>();
        braces.put('(', ')');
        braces.put('[', ']');

        // if length of string is odd, then it is not balanced
        if (str.length() % 2 != 0) {
            return false;
        }

        // travel half until openings are found and compare with
        // remaining if the closings matches
        Deque<Character> halfBraces = new LinkedList<>();
        for (char ch : str.toCharArray()) {
            if (braces.containsKey(ch)) {
                halfBraces.push(braces.get(ch));
            }
            // if stack is empty or if closing bracket is not equal to top of stack,
            // then braces are not balanced
            else if (halfBraces.isEmpty() || ch != halfBraces.pop()) {
                return false;
            }
        }
        return halfBraces.isEmpty();
    }
}
