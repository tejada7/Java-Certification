package com.javacertification.puzzles.javacodechallenges;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.function.UnaryOperator;

import static org.assertj.core.api.BDDAssertions.then;

class HundredDaysFromNowTest {

    private final UnaryOperator<LocalDate> hundredDaysFromNow = new HundredDaysFromNow();

    @Test
    void should_return_100_days_from_now() {
        // Given
        final var aDate = LocalDate.of(2021, 12, 18);

        // When
        final LocalDate actual = hundredDaysFromNow.apply(aDate);
        
        // Then
        then(actual).isEqualTo("2022-03-28");
    }
}