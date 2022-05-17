package com.javacertification.ocp.patternmatching;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;

@DisplayNameGeneration(ReplaceUnderscores.class)
class ReassingmentDemoTest {

    private ReassingmentDemo reassingmentDemo = new ReassingmentDemo();

    @ParameterizedTest(name = "[{index}] Comparing {0} against {1}.")
    @MethodSource("inputCombinations")
    void should_cast_number_types_and_compare_them(final Number num1, final Number num2) {
        // When
        final var actual = reassingmentDemo.compare(num1, num2);

        // Then
        then(actual).isZero();
    }

    private static Stream<Arguments> inputCombinations() {
        return Stream.of(
                Arguments.of(1, 1),
                Arguments.of(1f, 1f),
                Arguments.of(1d, 1d),
                Arguments.of(1L, 1L)
        );
    }
}
