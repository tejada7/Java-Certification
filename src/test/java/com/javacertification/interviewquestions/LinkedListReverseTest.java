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
public class LinkedListReverseTest {

    @Parameters
    public static Collection data() {
        return asList(new Object[][]{
                {new Integer[]{1, 2, 3, 4, 5}, "[1->2->3->4->5]", "[5->4->3->2->1]"},
                {new Integer[]{1, 2, 3}, "[1->2->3]", "[3->2->1]"},
                {new Integer[]{1, 2}, "[1->2]", "[2->1]"},
                {new Integer[]{1}, "[1]", "[1]"},
                {null, "[]", "[]"},
        });
    }

    @Parameter(value = 0)
    public Integer[] dataSet;

    @Parameter(value = 1)
    public String outputBeforeReversing;

    @Parameter(value = 2)
    public String expectedOutput;

    @Test
    public void reverseTest() {
        // Given
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(dataSet);

        assertEquals(outputBeforeReversing, linkedList.toString());

        // When
        linkedList.reverse();

        // Then
        assertEquals(expectedOutput, linkedList.toString());
    }
}
