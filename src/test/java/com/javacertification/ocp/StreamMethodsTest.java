package com.javacertification.ocp;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.javacertification.ocp.StreamMethods.MusicalGender.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StreamMethodsTest {

    StreamMethods streamMethods = new StreamMethods();

    @Test
    void decimalByBinary() {
        // Given
        var input = List.of(1, 1, 0, 2, 3, 5, 7);
        var expected = Map.of(0, 0,
                1, 1,
                2, 10,
                3, 11,
                5, 101,
                7, 111);

        // When
        var actual = streamMethods.decimalByBinary(input);

        // Then
        assertEquals(expected, actual);
        int i = 1234567890;      float f = i;      System.out.println(i - (int)f);
    }

    @Test
    void getAverageRatingByMusicalGender() {
        // Given
        var jimeno = new StreamMethods.Person(28, "Jimeno", List.of(new StreamMethods.Preference(Map.of(ROCK, 5)),
                new StreamMethods.Preference(Map.of(POP, 2)),
                new StreamMethods.Preference(Map.of(COUNTRY, 0)),
                new StreamMethods.Preference(Map.of(HIP_HOP, 2))));

        var gino = new StreamMethods.Person(20, "Gino", List.of(new StreamMethods.Preference(Map.of(ROCK, 0)),
                new StreamMethods.Preference(Map.of(POP, 2)),
                new StreamMethods.Preference(Map.of(COUNTRY, 1)),
                new StreamMethods.Preference(Map.of(HIP_HOP, 5))));

        var marduk = new StreamMethods.Person(28, "Marduk", List.of(new StreamMethods.Preference(Map.of(ROCK, 5)),
                new StreamMethods.Preference(Map.of(POP, 0)),
                new StreamMethods.Preference(Map.of(COUNTRY, 0)),
                new StreamMethods.Preference(Map.of(HIP_HOP, 0))));
        // When
        var actual = streamMethods.getAverageRatingByMusicalGender(List.of(jimeno, gino, marduk));

        // Then
        System.out.println(actual);
        assertEquals(BigDecimal.valueOf(3.33), actual.get(ROCK));
        assertEquals(BigDecimal.valueOf(1.33), actual.get(POP));
        assertEquals(BigDecimal.valueOf(.333), actual.get(COUNTRY));
        assertEquals(BigDecimal.valueOf(2.33), actual.get(HIP_HOP));
    }

    @Test
    void asd() {
        List<String> life = List.of();
        life.stream()
                .filter(keys -> anyOf("organization", "motivation", "consistency"))
                .collect(Collectors.toSet());
    }

    private boolean anyOf(String organization, String motivation, String hardwork) {
        return false;
    }

    class Life {

    }

}