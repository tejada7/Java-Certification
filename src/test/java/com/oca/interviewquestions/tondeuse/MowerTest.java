package com.oca.interviewquestions.tondeuse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MowerTest {

    /**
     * Dataset.
     *
     * @return a collection that contains the value to which we evaluate to know if it belong to a power of 2 and the
     * expected result.
     */
    @Parameterized.Parameters
    public static Collection<Object> getData() {
        return asList(new Object[][]{
                {asList("5 5",
                        "1 2 N",
                        "GAGAGAGAA",
                        "3 3 E",
                        "AADAADADDA"), Arrays.asList("2 3 N", "5 1 E")}
        });
    }

    @Parameterized.Parameter
    public List<String> input;

    @Parameterized.Parameter(value = 1)
    public List<String> expectedOutput;

    @Test
    public void mowerTestFromCodeInput() {
        assertEquals(expectedOutput, MowerLauncher.processInput(input, false));
    }

    @Test
    public void mowerTestFromExternalFile() throws URISyntaxException, IOException {
        final URL resource = getClass().getClassLoader().getResource("mower_test_case_1.txt");
        List<String> lines = Files.readAllLines(Paths.get(resource.toURI()));
        assertEquals(expectedOutput, MowerLauncher.processInput(lines, false));
    }
}