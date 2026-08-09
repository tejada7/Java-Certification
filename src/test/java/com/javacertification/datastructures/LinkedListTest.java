package com.javacertification.datastructures;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.*;
import static org.assertj.core.api.BDDSoftAssertions.thenSoftly;

class LinkedListTest {

    @Test
    void add() {
        // Given
        LinkedList<Integer> linkedList = new LinkedList<>();

        // When
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        // Then
        then(linkedList).hasToString("[1->2->3]");
    }

    @Test
    void insert() {
        // Given
        LinkedList<Integer> linkedList = new LinkedList<>();

        // When
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        linkedList.insert(0, 0);
        linkedList.insert(4, 4);
        linkedList.insert(2, 2);

        // Then
        then(linkedList).hasToString("[0->1->2->2->3->4]");
    }

    @Test
    void whenInsertingAtInvalidIndex_shouldThrowException() {
        then(catchRuntimeException(() -> new LinkedList<String>().insert("", 2)))
            .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void removeAt() {
        // Given
        LinkedList<Integer> linkedList = new LinkedList<>();

        // When
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);

        linkedList.removeAt(1); // {1 3 4}
        linkedList.removeFirst(); // { 3 4}

        // Then
        then(linkedList).hasToString("[3->4]");
    }

    @Test
    void randomInsertAndRemoveOperations() {
        LinkedList<Integer> linkedList = new LinkedList<>();

        linkedList.add(1); // {1}
        assertThat(linkedList).hasToString("[1]");

        linkedList.insert(2, 1); // {1 2}
        assertThat(linkedList).hasToString("[1->2]");

        linkedList.removeAt(1); // {1}
        assertThat(linkedList).hasToString("[1]");

        linkedList.insert(0, 1); // {1 0}
        assertThat(linkedList).hasToString("[1->0]");

        linkedList.add(4); // {1 0 4}
        assertThat(linkedList).hasToString("[1->0->4]");

        linkedList.removeAt(2); // {1 0};
        assertThat(linkedList).hasToString("[1->0]");

        linkedList.removeFirst(); // {0}
        assertThat(linkedList).hasToString("[0]");

        linkedList.removeAt(0); // {}
        assertThat(linkedList).hasToString("[]");

        linkedList.insert(1, 0); // {}
        assertThat(linkedList).hasToString("[1]");

        linkedList.removeFirst(); // {0}
        assertThat(linkedList).hasToString("[]");

        thenIllegalStateException().isThrownBy(linkedList::removeFirst);
    }

    @Test
    void get() {
        // Given
        LinkedList<Integer> linkedList = new LinkedList<>();

        // When
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);

        // Then
        thenSoftly(softly -> {
            softly.then(catchRuntimeException(() -> linkedList.get(5)))
                .isInstanceOf(IndexOutOfBoundsException.class);
            softly.then(linkedList.get(0)).isEqualTo(1);
            softly.then(linkedList.get(4)).isEqualTo(5);
            softly.then(linkedList.get(2)).isEqualTo(3);
        });
    }

    @Test
    void find() {
        // Given
        LinkedList<Integer> linkedList = new LinkedList<>();

        // When
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);

        // Then
        thenSoftly(softly -> {
            softly.then(catchRuntimeException(() -> linkedList.find(6)))
                .isInstanceOf(NoSuchElementException.class);
            softly.then(linkedList.get(0)).isEqualTo(1);
            softly.then(linkedList.get(4)).isEqualTo(5);
            softly.then(linkedList.get(2)).isEqualTo(3);
        });
    }
}
