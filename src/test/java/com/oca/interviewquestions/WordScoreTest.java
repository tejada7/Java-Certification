package com.oca.interviewquestions;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.runners.Parameterized.*;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class WordScoreTest {

    private static final String LONG_STRING = "I don't mind\n" +
            "I don't care\n" +
            "As long as you're here\n" +
            "Go ahead, tell me you'll leave again\n" +
            "You'll just come back running\n" +
            "Holding your scarred heart in hand\n" +
            "It's all the same\n" +
            "And I'll take you for who you are\n" +
            "If you take me for everything\n" +
            "And do it all over again\n" +
            "It's all the same\n" +
            "Hours slide and days go by\n" +
            "'Til you decide to come\n" +
            "But in-between\n" +
            "It always seems\n" +
            "Too long for certain";

    @Parameters
    public static Collection getData() {
        return asList(new Object[][]{
                {"XRay Machine", 20},
                {"Jabbt", 13}
        });
    }

    @Parameter
    public String input;

    @Parameter(value = 1)
    public int expectedScore;

    @Test
    public void testWordScore() {
        Assert.assertEquals(expectedScore, new WordScore().applyAsInt(input));
    }

    @Test
    public void testWordScore_shouldThrowException_whenNullInput() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new WordScore().applyAsInt(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWordScore_shouldThrowException_whenInputGreaterThan50() {
        new WordScore().applyAsInt(LONG_STRING);
    }
}
