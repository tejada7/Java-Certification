package com.javacertification;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.DisplayNameGenerator.IndicativeSentences;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;

@DisplayName("An status calculator")
@DisplayNameGeneration(IndicativeSentences.class)
@IndicativeSentencesGeneration(separator = " ", generator = ReplaceUnderscores.class)
class StatsCalculatorTest {

    @Test
    void should_calculate_stats_for_a_bunch_of_integers() {
        // Given
        final int[] integers = new int[]{6, 9, 15, -2, 92, 11};

        // When
        final String actual = new StatsCalculator().calculate(integers);

        // Then
        then(actual).isEqualTo("""
                minimum value = -2\
                maximum value = 92\
                number of elements in the sequence = 6\
                average value = 21.833333
                """);
    }
}
