package com.javacertification.datastructures;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDSoftAssertions.thenSoftly;

class BinaryTreeTest {

    @Test
    void fourElementArrayTest() {
        // Given
        Integer[] array = new Integer[]{1, 2, 3, 4};

        // When
        BinaryTree<Integer> tree = new BinaryTree<>(array);

        // Then
        thenSoftly(softly -> {
            softly.then(tree.getRoot().value.intValue()).isEqualTo(2);
            softly.then(tree.getRoot().left.value.intValue()).isEqualTo(1);
            softly.then(tree.getRoot().right.value.intValue()).isEqualTo(3);
            softly.then(tree.getRoot().right.right.value.intValue()).isEqualTo(4);
        });
        // 2
        // ┌─┴─┐
        // 1 3
        // └─┐
        // 4
    }

    @Test
    void thirteenElementArrayTest() {
        // Given
        Integer[] array = new Integer[]{1, 2, 4, 5, 6, 8, 9, 10,
                11, 12, 13, 14, 15};

        // When
        BinaryTree<Integer> tree = new BinaryTree<>(array);

        // Then
        thenSoftly(softly -> {
            softly.then(tree.getRoot().value.intValue()).isEqualTo(9);
            softly.then(tree.getRoot().left.value.intValue()).isEqualTo(4);
            softly.then(tree.getRoot().right.value.intValue()).isEqualTo(12);
            softly.then(tree.getRoot().left.left.value.intValue()).isEqualTo(1);
            softly.then(tree.getRoot().left.right.value.intValue()).isEqualTo(6);
            softly.then(tree.getRoot().right.left.value.intValue()).isEqualTo(10);
            softly.then(tree.getRoot().right.right.value.intValue()).isEqualTo(14);
            softly.then(tree.getRoot().left.left.right.value.intValue()).isEqualTo(2);
            softly.then(tree.getRoot().left.right.left.value.intValue()).isEqualTo(5);
            softly.then(tree.getRoot().left.right.right.value.intValue()).isEqualTo(8);
            softly.then(tree.getRoot().right.left.right.value.intValue()).isEqualTo(11);
            softly.then(tree.getRoot().right.right.left.value.intValue()).isEqualTo(13);
            softly.then(tree.getRoot().right.right.right.value.intValue()).isEqualTo(15);
        });
        // 9
        // ┌──────┴─────┐
        // 4 12
        // ┌───┴───┐ ┌───┴───┐
        // 1 6 10 14
        // └─┐ ┌─┴─┐ └─┐ ┌─┴─┐
        // 2 5 8 11 13 15
    }
}
