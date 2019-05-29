package com.oca.interviewquestions;

import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Util class for manipulating DNA characters.
 */
public class DNAUtils {
    private static final Pattern PATTERN = Pattern.compile("(?=([ACTG]{10}).*\\1)");

    private DNAUtils() {
        throw new IllegalStateException("Constructor not allowed here.");
    }

    /**
     * Find all ten letter long sequences which occur more than once within the DNA.
     *
     * @param dna the dna sequence
     * @return a {@link Collection} object containing the repeated sequences
     */
    public static Collection<String> repeatedDna(String dna) {
        Collection<String> result = new HashSet<>();
        Matcher matcher = PATTERN.matcher(dna);
        while (matcher.find()) {
            result.add(matcher.group(1));
        }
        return result;
    }
}
