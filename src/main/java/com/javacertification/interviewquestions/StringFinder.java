package com.javacertification.interviewquestions;

import java.util.HashSet;

class StringFinder {

    /**
     * Given a string, find the first repeated character in it.
     * We need to find the character that occurs more than once and whose index of second occurrence is smallest.
     *
     * @param s the input
     * @return the first repeated character
     * <p>
     * examples:
     * "nwcn", → n
     * "abczcbaacz" → c
     * "abcdd" → d
     */
    public char firstRepeatedCharacterQuadraticSolution(String s) {
        final var chars = s.toCharArray();
        int position = 100;
        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] == chars[j] && j < position) {
                    position = j;
                }
            }
        }
        return position == 100
                ? '\u0000'
                : chars[position];
    }

    public char firstRepeatedCharacterLinealSolution(String s) {
        final var array = s.toCharArray();
        final var set = new HashSet<>();
        for (final var element : array) {
            final var result = set.add(element);
            if (!result) {
                return element;
            }
        }
        return '\u0000';
    }
}
