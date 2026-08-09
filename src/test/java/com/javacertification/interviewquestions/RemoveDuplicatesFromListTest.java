package com.javacertification.interviewquestions;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static com.javacertification.interviewquestions.ListUtils.removeDuplicates;
import static java.util.Arrays.asList;
import static org.assertj.core.api.BDDAssertions.then;

class RemoveDuplicatesFromListTest {

    private static Stream<Arguments> data() {
        return Stream.of(
            Arguments.of(asList(1, 1, 2, 2, 3, 3), asList(1, 2, 3)),
            Arguments.of(asList("a", "a", "b", "c", "b"), asList("a", "b", "c")),
            Arguments.of(asList(5f, 4f, 5f, 4f), asList(5f, 4f)),
            Arguments.of(null, Collections.emptyList()),
            Arguments.of(asList(1, 5f, 3d, 4L, 4, 9, "a", "@", 0, null, null, "@"),
                asList(1, 5f, 3d, 4L, 4, 9, "a", "@", 0, null))
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void shouldRemoveDuplicates_andKeepOrder(List<?> originalList, List<?> expectedList) {
        then(removeDuplicates(originalList)).isEqualTo(expectedList);
    }
}
