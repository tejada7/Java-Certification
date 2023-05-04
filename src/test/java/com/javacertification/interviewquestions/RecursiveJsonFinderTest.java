package com.javacertification.interviewquestions;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.*;

@DisplayNameGeneration(ReplaceUnderscores.class)
class RecursiveJsonFinderTest {

    @Test
    void should_find_existing_path_from_json() {
        // Given
        final var input = """
                {
                    "foo": "1",
                    "foo1": {
                        "foo2": "2"
                    }
                }
                """;
        final var pathToFind = "foo1.foo2";

        // When
        final Object actual = new RecursiveJsonFinder().apply(input, pathToFind);

        // Then
        then(actual).isEqualTo("2");
    }
}
