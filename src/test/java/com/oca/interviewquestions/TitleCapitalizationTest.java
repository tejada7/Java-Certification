package com.oca.interviewquestions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TitleCapitalizationTest {

    private TitleCapitalization titleCapitalization;

    @BeforeEach
    void setUp() {
        titleCapitalization = new TitleCapitalization();
    }

    @Test
    void testTitleCapitalization() {
        assertEquals("I Love Solving Problems and It Is Fun", titleCapitalization
                .apply("i love solving problems and it is fun"));

        assertEquals("Why Does a Bird Fly?", titleCapitalization
                .apply("wHy DoeS A biRd Fly?"));
    }

    @Test
    void testTitleCapitalization_shouldThrowException_whenNullInput() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> titleCapitalization.apply(null));
    }
}
