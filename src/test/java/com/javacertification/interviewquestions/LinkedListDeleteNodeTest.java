package com.javacertification.interviewquestions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class LinkedListDeleteNodeTest {

    @Parameters
    public static Collection data() {
        return asList(new Object[][]{
                {new Integer[]{1, 2, 3, 4, 5}, 3, "[1->2->3->4->5]", "[1->2->4->5]"},
                {new Integer[]{1, 2, 3}, 1, "[1->2->3]", "[2->3]"},
                {new Integer[]{1, 2}, 3, "[1->2]", "[1->2]"},
                {new Integer[]{1}, 1, "[1]", "[]"},
                {null, 4, "[]", "[]"}
        });
    }

    @Parameter(value = 0)
    public Integer[] dataSet;

    @Parameter(value = 1)
    public Integer valueToDelete;

    @Parameter(value = 2)
    public String outputBeforeReversing;

    @Parameter(value = 3)
    public String expectedOutput;

    @Test
    public void reverseTest() {
        // Given
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(dataSet);

        assertEquals(outputBeforeReversing, linkedList.toString());

        // When
        linkedList.remove(valueToDelete);

        // Then
        assertEquals(expectedOutput, linkedList.toString());
    }
}
