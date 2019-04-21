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
public class ReverseTextTest {

    @Parameters/*(name = "({index}): reverse({1})={0}")*/
    public static Collection data() {
        return Arrays.asList(new Object[][]{
                {"", null},
                {"", ""},
                {"oruro", "oruro"},
                {"012345", "543210"},
                {"sDerG", "GreDs"}
        });
    }

    @Parameter(value = 0)
    public String expected;

    @Parameter(value = 1)
    public String input;

    @Test
    public void reverseText() {
        Assert.assertEquals(expected, ReverseText.reverse(input));
    }
}