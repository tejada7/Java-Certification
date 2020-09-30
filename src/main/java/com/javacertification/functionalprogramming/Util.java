package com.javacertification.functionalprogramming;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.Map.Entry;
import static java.util.stream.Collectors.*;

public class Util {

    private Util() {
        throw new IllegalStateException("Illegal constructor invokation.");
    }

    static boolean isPrime(int number) {
        boolean divisible = false;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                divisible = true;
                break;
            }
        }
        return number > 1 && !divisible;
    }

    static boolean isPrimeRefactored(int number) {
        return number > 1 && IntStream.range(2, number)
                .noneMatch(i -> number % i == 0);
    }

    static long countWordsInFile(String filePath, String searchWord) {
        long count = 0;
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));

            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(searchWord))
                    count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }

    static long countWordsInFileRefactored(String filePath, String searchWord) {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines
                    .filter(line -> line.contains(searchWord))
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    static Map<Integer, Set<String>> groupByScores(Map<String, Integer> scores) {
        Map<Integer, Set<String>> byScores = new HashMap<>();
        for (String name : scores.keySet()) {
            int score = scores.get(name);

            Set<String> names = new HashSet<>();
            if (byScores.containsKey(score))
                names = byScores.get(score);

            names.add(name);
            byScores.put(score, names);
        }

        return byScores;

    }

    static Map<Integer, Set<String>> groupByScoresRefactored(Map<String, Integer> scores) {
        return scores.entrySet()
                .stream()
                .collect(groupingBy(Entry::getValue, mapping(Entry::getKey, toSet())));

    }

    static Map<Integer, Long> countScores(Map<String, Integer> scores) {
        Map<Integer, Long> byScores = new HashMap<>();
        for (String name : scores.keySet()) {
            int score = scores.get(name);

            long count = 0;
            if (byScores.containsKey(score))
                count = byScores.get(score);

            byScores.put(score, count + 1);
        }

        return byScores;
    }

    static Map<Integer, Long> countScoresRefactored(Map<String, Integer> scores) {
        return scores.keySet()
                .stream()
                .collect(groupingBy(scores::get, counting()));
    }

    static Map<Integer, Set<Integer>> numberOfLetters(Map<String, Integer> scores) {
        Map<Integer, Set<Integer>> byScores = new HashMap<>();
        for (String name : scores.keySet()) {
            int score = scores.get(name);

            Set<Integer> letters = new HashSet<>();
            if (byScores.containsKey(score))
                letters = byScores.get(score);

            letters.add(name.length());
            byScores.put(score, letters);
        }

        return byScores;
    }

    static Map<Integer, Set<Integer>> numberOfLettersRefactored(Map<String, Integer> scores) {
        return scores.keySet()
                .stream()
                .collect(groupingBy(scores::get, mapping(String::length, toSet())));
    }

    static Map<Integer, Integer> maxNumberOfLetters(Map<String, Integer> scores) {
        Map<Integer, Integer> byScores = new HashMap<>();
        for (String name : scores.keySet()) {
            int score = scores.get(name);

            int maxLength = 0;
            if (byScores.containsKey(score))
                maxLength = byScores.get(score);

            maxLength = Math.max(maxLength, name.length());
            byScores.put(score, maxLength);
        }

        return byScores;
    }

    static Map<Integer, Integer> maxNumberOfLettersRefactored(Map<String, Integer> scores) {
        return scores.keySet()
                .stream()
                .collect(groupingBy(scores::get, collectingAndThen(maxBy(comparing(String::length)),
                        name -> name.orElse("").length())));
    }

    private static String getTriple(int m, int n) {
        int a = m * m - n * n;
        int b = 2 * m * n;
        int c = m * m + n * n;

        return String.format("%d %d %d", a, b, c);
    }

    static List<String> computeTriples(int numberOfValues) {
        List<String> triples = new ArrayList<>();
        int count = 1;

        for (int m = 2; ; m++) {
            for (int n = 1; n < m; n++) {
                triples.add(getTriple(m, n));
                count++;

                if (count > numberOfValues)
                    break;
            }

            if (count > numberOfValues)
                break;
        }

        return triples;
    }

    static List<String> computeTriplesRefactored(int numberOfValues) {
        return Stream.iterate(2, m -> m + 1)
                .flatMap(m -> IntStream.range(1, m)
                        .mapToObj(n -> getTriple(m, n)))
                .limit(numberOfValues)
                .collect(toList());
    }
}