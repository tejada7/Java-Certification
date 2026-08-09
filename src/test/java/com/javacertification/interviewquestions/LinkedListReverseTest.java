package com.javacertification.interviewquestions;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

class LinkedListReverseTest {

    private static Stream<Arguments> data() {
        return Stream.of(
            Arguments.of(new Integer[]{1, 2, 3, 4, 5}, "[1->2->3->4->5]", "[5->4->3->2->1]"),
            Arguments.of(new Integer[]{1, 2, 3}, "[1->2->3]", "[3->2->1]"),
            Arguments.of(new Integer[]{1, 2}, "[1->2]", "[2->1]"),
            Arguments.of(new Integer[]{1}, "[1]", "[1]"),
            Arguments.of(null, "[]", "[]")
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void reverseTest(Integer[] dataSet, String outputBeforeReversing, String expectedOutput) {
        // Given
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(dataSet);
        assertThat(linkedList).hasToString(outputBeforeReversing);

        // When
        linkedList.reverse();

        // Then
        then(linkedList).hasToString(expectedOutput);
    }
}
