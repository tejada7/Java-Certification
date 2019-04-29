package com.oca.datastructures;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.NoSuchElementException;

public class LinkedListTest {

    @Test
    public void add() {
        // Given
        LinkedList<Integer> linkedList = new LinkedList<>();

        // When
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        // Then
        Assert.assertEquals("[1->2->3]", linkedList.toString());
    }

    @Test
    public void insert() {
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
        Assert.assertEquals("[0->1->2->2->3->4]", linkedList.toString());
    }

    @Test
    public void whenInsertingAtInvalidIndex_shouldThrowException() {
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> new LinkedList<String>().insert("", 2));
    }

    @Test
    public void removeAt() {
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
        Assert.assertEquals("[3->4]", linkedList.toString());
    }

    @Test
    public void randomInsertAndRemoveOperations() {
        LinkedList<Integer> linkedList = new LinkedList<>();

        linkedList.add(1); // {1}
        Assert.assertEquals("[1]", linkedList.toString());

        linkedList.insert(2, 1); // {1 2}
        Assert.assertEquals("[1->2]", linkedList.toString());

        linkedList.removeAt(1); // {1}
        Assert.assertEquals("[1]", linkedList.toString());

        linkedList.insert(0, 1); // {1 0}
        Assert.assertEquals("[1->0]", linkedList.toString());

        linkedList.add(4); // {1 0 4}
        Assert.assertEquals("[1->0->4]", linkedList.toString());

        linkedList.removeAt(2); // {1 0};
        Assert.assertEquals("[1->0]", linkedList.toString());

        linkedList.removeFirst(); // {0}
        Assert.assertEquals("[0]", linkedList.toString());

        linkedList.removeAt(0); // {}
        Assert.assertEquals("[]", linkedList.toString());

        linkedList.insert(1, 0); // {}
        Assert.assertEquals("[1]", linkedList.toString());

        linkedList.removeFirst(); // {0}
        Assert.assertEquals("[]", linkedList.toString());

        Assertions.assertThrows(IllegalStateException.class, linkedList::removeFirst);
    }

    @Test
    public void get() {
        // Given
        LinkedList<Integer> linkedList = new LinkedList<>();

        // When
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);

        // Then
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(5));
        Assert.assertEquals(Integer.valueOf(1), linkedList.get(0));
        Assert.assertEquals(Integer.valueOf(5), linkedList.get(4));
        Assert.assertEquals(Integer.valueOf(3), linkedList.get(2));
    }

    @Test
    public void find() {
        // Given
        LinkedList<Integer> linkedList = new LinkedList<>();

        // When
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);

        // Then
        Assertions.assertThrows(NoSuchElementException.class, () -> linkedList.find(6));
        Assert.assertEquals(0, linkedList.find(1));
        Assert.assertEquals(4, linkedList.find(5));
        Assert.assertEquals(2, linkedList.find(3));
    }
}
