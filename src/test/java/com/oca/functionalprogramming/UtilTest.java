package com.oca.functionalprogramming;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @ParameterizedTest
    @MethodSource("prime")
    void isPrime(Function<Integer, Boolean> function) {
        assertAll(
                () -> assertFalse(function.apply(-2)),
                () -> assertFalse(function.apply(0)),
                () -> assertFalse(function.apply(1)),
                () -> assertTrue(function.apply(2)),
                () -> assertTrue(function.apply(3)),
                () -> assertFalse(function.apply(4)),
                () -> assertTrue(function.apply(5)),
                () -> assertTrue(function.apply(7)),
                () -> assertFalse(function.apply(9)),
                () -> assertTrue(function.apply(11))
        );
    }

    static Stream<Function<Integer, Boolean>> prime() {
        return Stream.of(Util::isPrime, Util::isPrimeRefactored);
    }

    @ParameterizedTest
    @MethodSource("wordsInFile")
    void countWordsInFile(BiFunction<String, String, Long> function) {
        String fileName = "./src/main/java/com/oca/functionalprogramming/Util.java";

        assertAll(
                () -> assertEquals(0, function.apply(fileName, "nope")),
                () -> assertEquals(2, function.apply(fileName, "isPrime"))
        );
    }

    static Stream<BiFunction<String, String, Long>> wordsInFile() {
        return Stream.of(Util::countWordsInFile, Util::countWordsInFileRefactored);
    }

    private final Map<String, Integer> scores = new HashMap<>();
    private final Map<Integer, Set<String>> expected = new HashMap<>();

    @ParameterizedTest
    @MethodSource("groupByScores")
    void groupByScoresEmptyMap(Function<Map<String, Integer>, Map<Integer, Set<String>>> function) {
        assertEquals(expected, function.apply(scores));
    }

    @ParameterizedTest
    @MethodSource("groupByScores")
    void groupByScoresWithOneScore(Function<Map<String, Integer>, Map<Integer, Set<String>>> function) {
        scores.put("Jill", 15);

        expected.put(15, new HashSet<>(List.of("Jill")));

        assertEquals(expected, function.apply(scores));
    }

    @ParameterizedTest
    @MethodSource("groupByScores")
    void groupByScoresWithTwoScores(Function<Map<String, Integer>, Map<Integer, Set<String>>> function) {
        scores.put("Jill", 15);
        scores.put("Jack", 12);

        expected.put(15, new HashSet<>(List.of("Jill")));
        expected.put(12, new HashSet<>(List.of("Jack")));

        assertEquals(expected, function.apply(scores));
    }

    @ParameterizedTest
    @MethodSource("groupByScores")
    void groupByScoresWithThreeScoresWithOneTied(Function<Map<String, Integer>, Map<Integer, Set<String>>> function) {
        scores.put("Pam", 12);
        scores.put("Jill", 15);
        scores.put("Jack", 12);

        expected.put(15, new HashSet<>(List.of("Jill")));
        expected.put(12, new HashSet<>(List.of("Jack", "Pam")));

        assertEquals(expected, function.apply(scores));
    }

    @ParameterizedTest
    @MethodSource("groupByScores")
    void groupByScoresWithMultipleScores(Function<Map<String, Integer>, Map<Integer, Set<String>>> function) {
        scores.put("Pam", 12);
        scores.put("Jill", 15);
        scores.put("Jack", 12);
        scores.put("Tom", 11);
        scores.put("Darla", 15);
        scores.put("Raj", 15);
        scores.put("Nguyen", 11);

        expected.put(11, new HashSet<>(List.of("Tom", "Nguyen")));
        expected.put(12, new HashSet<>(List.of("Pam", "Jack")));
        expected.put(15, new HashSet<>(List.of("Jill", "Darla", "Raj")));

        assertEquals(expected, function.apply(scores));
    }

    static Stream<Function<Map<String, Integer>, Map<Integer, Set<String>>>> groupByScores() {
        return Stream.of(Util::groupByScores, Util::groupByScoresRefactored);
    }

    final Map<String, Integer> scoresData = Map.of(
            "Jack", 12,
            "Jill", 15,
            "Tom", 11,
            "Darla", 15,
            "TOM", 11,
            "Raj", 15,
            "Nguyen", 11);

    @ParameterizedTest
    @MethodSource("countScores")
    void scoresCount(Function<Map<String, Integer>, Map<Integer, Long>> function) {
        assertAll(
                () -> assertEquals(1, function.apply(scoresData).get(12))
                , () -> assertEquals(3, function.apply(scoresData).get(11))
                , () -> assertEquals(3, function.apply(scoresData).get(15))
        );
    }

    static Stream<Function<Map<String, Integer>, Map<Integer, Long>>> countScores() {
        return Stream.of(Util::countScores, Util::countScoresRefactored);
    }

    @ParameterizedTest
    @MethodSource("numberOfLetters")
    void shouldCalculateNumberOfLetters(Function<Map<String, Integer>, Map<Integer, Set<Integer>>> function) {
        assertAll(
                () -> assertTrue(function.apply(scoresData).get(12).contains(4))
                , () -> assertTrue(function.apply(scoresData).get(15).contains(5))
                , () -> assertTrue(function.apply(scoresData).get(15).contains(4))
                , () -> assertTrue(function.apply(scoresData).get(11).contains(6))
                , () -> assertTrue(function.apply(scoresData).get(11).contains(3))
        );
    }

    static Stream<Function<Map<String, Integer>, Map<Integer, Set<Integer>>>> numberOfLetters() {
        return Stream.of(Util::numberOfLetters, Util::numberOfLettersRefactored);
    }

    @ParameterizedTest
    @MethodSource("maxNumberOfLetters")
    void shouldCalculateMaxNumberOfLetters(Function<Map<String, Integer>, Map<Integer, Integer>> function) {
        assertAll(
                () -> assertEquals(4, function.apply(scoresData).get(12))
                , () -> assertEquals(5, function.apply(scoresData).get(15))
                , () -> assertEquals(6, function.apply(scoresData).get(11))
        );
    }

    static Stream<Function<Map<String, Integer>, Map<Integer, Integer>>> maxNumberOfLetters() {
        return Stream.of(Util::maxNumberOfLetters, Util::maxNumberOfLettersRefactored);
    }

    @ParameterizedTest
    @MethodSource("calculateTriples")
    void numberAtPosition(Function<Integer, List<String>> function) {
        assertAll(
                () -> assertEquals(List.of("3 4 5"), function.apply(1))
                , () -> assertEquals(List.of("3 4 5", "8 6 10"), function.apply(2))
                , () -> assertEquals(List.of("3 4 5", "8 6 10", "5 12 13"), function.apply(3))
                , () -> assertEquals(List.of("3 4 5", "8 6 10", "5 12 13", "15 8 17"), function.apply(4))
                , () -> assertEquals(List.of("3 4 5", "8 6 10", "5 12 13", "15 8 17", "12 16 20"), function.apply(5))
                , () -> assertEquals(10, function.apply(10).size())
                , () -> assertTrue(function.apply(10).contains("21 20 29"))
                , () -> assertEquals(20, function.apply(20).size())
                , () -> assertTrue(function.apply(20).contains("45 28 53"))
        );
    }

    static Stream<Function<Integer, List<String>>> calculateTriples() {
        return Stream.of(Util::computeTriples, Util::computeTriplesRefactored);
    }
}