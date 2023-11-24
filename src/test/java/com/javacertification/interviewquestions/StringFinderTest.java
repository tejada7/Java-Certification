package com.javacertification.interviewquestions;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.BDDSoftAssertions.thenSoftly;

@DisplayNameGeneration(ReplaceUnderscores.class)
class StringFinderTest {

    private final StringFinder finder = new StringFinder();

    @ParameterizedTest
    @CsvSource(textBlock = """
            nwcn, n
            abczcbaacz, c
            abcdd, d
             """)
    void should_find_first_repeated_character(String input, char expected) {
        thenSoftly(softly -> {
            softly.then(finder.firstRepeatedCharacterQuadraticSolution(input)).isEqualTo(expected);
            softly.then(finder.firstRepeatedCharacterLinealSolution(input)).isEqualTo(expected);
        });
    }
}
