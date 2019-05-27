package com.oca.datastructures;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinaryTreeTest {

    @Test
    public void fourElementArrayTest() {
        Integer[] array = new Integer[]{1, 2, 3, 4};
        BinaryTree<Integer> tree = new BinaryTree<>(array);
        assertEquals(2, tree.getRoot().value.intValue());
        assertEquals(1, tree.getRoot().left.value.intValue());
        assertEquals(3, tree.getRoot().right.value.intValue());
        assertEquals(4, tree.getRoot().right.right.value.intValue());
        // 2
        // ┌─┴─┐
        // 1 3
        // └─┐
        // 4
    }

    @Test
    public void thirteenElementArrayTest() {
        Integer[] array = new Integer[]{1, 2, 4, 5, 6, 8, 9, 10,
                11, 12, 13, 14, 15};
        BinaryTree<Integer> tree = new BinaryTree<>(array);
        assertEquals(9, tree.getRoot().value.intValue());
        assertEquals(4, tree.getRoot().left.value.intValue());
        assertEquals(12, tree.getRoot().right.value.intValue());
        assertEquals(1, tree.getRoot().left.left.value.intValue());
        assertEquals(6, tree.getRoot().left.right.value.intValue());
        assertEquals(10, tree.getRoot().right.left.value.intValue());
        assertEquals(14, tree.getRoot().right.right.value.intValue());
        assertEquals(2, tree.getRoot().left.left.right.value.intValue());
        assertEquals(5, tree.getRoot().left.right.left.value.intValue());
        assertEquals(8, tree.getRoot().left.right.right.value.intValue());
        assertEquals(11, tree.getRoot().right.left.right.value.intValue());
        assertEquals(13, tree.getRoot().right.right.left.value.intValue());
        assertEquals(15, tree.getRoot().right.right.right.value.intValue());
        // 9
        // ┌──────┴─────┐
        // 4 12
        // ┌───┴───┐ ┌───┴───┐
        // 1 6 10 14
        // └─┐ ┌─┴─┐ └─┐ ┌─┴─┐
        // 2 5 8 11 13 15
    }
}