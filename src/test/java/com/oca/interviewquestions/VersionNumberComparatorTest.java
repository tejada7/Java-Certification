package com.oca.interviewquestions;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.runners.Parameterized.*;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class VersionNumberComparatorTest {

    @Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
                {"2.2.5", "2.3", -1},
                {"3.0", "2.1.5.3", 1},
                {"3", "4.0", -1},
                {"12.5.1", "12.5.2", -1},
                {"12.5.1", "12.5.1", 0},
                {"12.6.1", "12.5.1", 1},
                {"14.10.55", "14.10.20", 1},
                {"14.13.10", "14.10.55", 1},
                {"14.13.10", "15.1", -1}
        });
    }

    @Parameter(value = 0)
    public String version1;

    @Parameter(value = 1)
    public String version2;

    @Parameter(value = 2)
    public int expected;

    @Test
    public void compareVersionTest() {
        Assert.assertEquals(expected, new VersionNumberComparator().compare(version1, version2));
    }
}