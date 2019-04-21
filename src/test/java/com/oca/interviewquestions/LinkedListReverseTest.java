package com.oca.interviewquestions;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class LinkedListReverseTest {

    @Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
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

        Assert.assertEquals(outputBeforeReversing, linkedList.toString());

        // When
        linkedList.reverse();

        // Then
        Assert.assertEquals(expectedOutput, linkedList.toString());
    }
}
