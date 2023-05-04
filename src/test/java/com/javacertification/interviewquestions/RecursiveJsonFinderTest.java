package com.javacertification.interviewquestions;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;

@DisplayNameGeneration(ReplaceUnderscores.class)
class RecursiveJsonFinderTest {

    private final RecursiveJsonFinder recursiveJsonFinder = new RecursiveJsonFinder();

    public static Stream<Arguments> fixture() {
        return Stream.of(
                Arguments.of("""
                        {
                            "foo": "1",
                            "foo1": {
                                "foo2": "2"
                            }
                        }
                        """, "foo1.foo2", "2"),
                Arguments.of(
                        """
                                {
                                    "foo": "1",
                                    "foo1": {
                                        "foo2": "2"
                                    },
                                    "foo3": "3"
                                }
                                """, "foo3", "3"),
                Arguments.of(
                        """
                                {
                                    "foo": "1",
                                    "foo1": {
                                        "foo2": "2"
                                    },
                                    "foo3": "3"
                                }
                                """, "foo1", """
                                {\
                                "foo2":"2"\
                                }\
                                """
                )
        );
    }

    @ParameterizedTest
    @MethodSource("fixture")
    void should_find_existing_path_from_json(final String input, final String pathToFind, final String expected) {
        then(recursiveJsonFinder.apply(input, pathToFind)).contains(expected);
    }

    @Test
    void should_return_empty_when_path_not_found() {
        // Given
        final var input = """
                {
                    "foo": "1",
                    "foo1": {
                        "foo2": "2"
                    },
                    "foo3": "3"
                }
                """;
        final var pathToFind = "foo4";

        // When
        final var actual = recursiveJsonFinder.apply(input, pathToFind);

        // Then
        then(actual).isEmpty();
    }
}
