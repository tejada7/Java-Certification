package com.oca.interviewquestions;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.function.UnaryOperator;

import static com.oca.interviewquestions.StringUtils.*;

/**
 * <p>
 * Many writer are often confused by the different methods of capitalizing titles.
 * There are several forms of capitalization rules, but one of the most popular is called "title case" or "up style".
 * The implementation of this {@link UnaryOperator} takes a title in the form of a {@link String} object and return its
 * value with the correct capitalization for a title according to these rules:
 * </p>
 * <ul>
 * <li>Always capitalize the first word in a title.</li>
 * <li>Always capitalize the last word in a title.</li>
 * <li>Lowercase the following words, unless they are the first or the last word of the title: "a", "the", "to", "at",
 * "in", "with", "and", "but", "or"</li>
 * </ul>
 * <p>
 * A word is defined as a series of non-space characters.
 */
public class TitleCapitalization implements UnaryOperator<String> {

    @Override
    public String apply(String title) {
        if (title == null) {
            throw new IllegalArgumentException("No null title is allowed.");
        }
        String[] words = title.split(SINGLE_SPACE);
        words = lowerCaseSpecialWords(capitalizeFirstLetter(words));
        final StringJoiner stringJoiner = new StringJoiner(SINGLE_SPACE, EMPTY_STRING, EMPTY_STRING);
        Arrays.stream(words).forEach(stringJoiner::add);
        return stringJoiner.toString();
    }

    private String[] lowerCaseSpecialWords(String[] words) {
        for (int i = 1; i < words.length - 1; i++) {
            switch (words[i].toLowerCase()) {
                case "a":
                case "the":
                case "to":
                case "at":
                case "in":
                case "with":
                case "and":
                case "but":
                case "or":
                    words[i] = words[i].toLowerCase();
                default:
                    // DO NOTHING
            }
        }
        return words;
    }
}
