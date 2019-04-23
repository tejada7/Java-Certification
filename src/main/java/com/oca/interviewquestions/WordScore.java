package com.oca.interviewquestions;

import java.util.function.ToIntFunction;

/**
 * <p>
 * In order to score a word, it adds up the point value of each letter in the word.
 * The word does not have to be a real word, just a string of characters.
 * The scoring for the system is:
 * F = 3, J = 6, X = 12, A, I, E, O = 2, T = 5 and all other letters are 0.
 * Is does not matter if the word is in caps or small letters.
 * This also ignores a non letter input and we assume the string is of at most 50 characters long.
 * </p>
 * <p>
 * Example 1: for the string "XRay Machine" -> the score would be 20
 * Example 2: for the string "Jabbt" -> the score would be 13
 * </p>
 */
public class WordScore implements ToIntFunction<String> {

    /**
     * {@inheritDoc}
     *
     * @param value the string to evaluate
     * @return the score defined as: F = 3, J = 6, X = 12, A, I, E, O = 2, T = 5 and all other letters are 0.
     * @throws IllegalArgumentException if {@code value} is <code>null</code> or if {@code}'s length is greater than 50
     */
    @Override
    public int applyAsInt(String value) {
        checkInputConditions(value);
        int score = 0;
        for (char letter : value.toLowerCase().toCharArray()) {
            switch (letter) {
                case 'f':
                    score += 3;
                    break;
                case 'j':
                    score += 6;
                    break;
                case 'x':
                    score += 12;
                    break;
                case 'a':
                case 'i':
                case 'e':
                case 'o':
                    score += 2;
                    break;
                case 't':
                    score += 5;
                    break;
                default:
                    // DO NOTHING
            }
        }
        return score;
    }

    private void checkInputConditions(String value) {
        if (value == null) {
            throw new IllegalArgumentException("No null parameter is allowed.");
        }
        if (value.length() > 50) {
            throw new IllegalArgumentException("The length of the input cannot be greater than 50 characters.");

        }
    }
}
