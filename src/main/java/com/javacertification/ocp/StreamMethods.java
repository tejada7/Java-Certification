package com.javacertification.ocp;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMethods {

    public static void main(String[] args) {
        final List<Integer> integerList = List.of();
        var x = integerList.stream().reduce((i, j) -> i * j);
        System.out.println(x);
        var array = new String[]{"w", "o", "l", "f", "!"};

        System.out.println(Arrays.stream(array).reduce(String::concat));
        final BiFunction<Integer, String, Integer> biFunction = (integer, s) -> integer + s.length();

        var merged = Stream.of(array).parallel().reduce(0, (identity, streamElement) -> identity + streamElement.length(), new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return 666;
            }
        });
        System.out.println(merged);

        var sb = Stream.of(array).parallel().collect(String::new, String::concat, String::concat);
        System.out.println(sb);

        var ts = Stream.of(array).collect(TreeSet::new, Set::add, Set::addAll);
        var ts1 = Stream.of(array).collect(Collectors.toCollection(TreeSet::new));
        System.out.println(ts);

        Stream.iterate(1, n -> n + 1)
                .skip(5)
                .limit(10)
                .forEach(System.out::println);


        Stream.of(array).sorted();
        var list = List.of("Toby", "Anna", "Leroy", "Alex");
        list.stream()
                .filter(name -> name.length() == 4)
                .sorted()
                .limit(2)
                .forEach(System.out::println);

        var infinite = Stream.iterate(1, xx -> xx + 1);
        infinite.parallel().limit(5)
                .peek(System.out::print)
                .filter(xx -> xx % 2 == 1)
                .forEach(System.out::print);

        // group list of names by lengths
        Map<Integer, List<String>> namesByLength = list.stream()
                .collect(Collectors.groupingBy(String::length));

        // group set of names by lengths
        Map<Integer, Set<String>> setNamesByLength = list.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.toSet()));
    }

    /**
     * Converts a collection of non-null integers to a map of unique decimals as key with its corresponding binary representation.
     *
     * @param integers the collection of integers
     * @return a map of integers.
     */
    public Map<Integer, Integer> decimalByBinary(Collection<Integer> integers) {
        return integers.stream()
                .collect(Collectors.toMap(Function.identity(),
                        e -> Integer.parseInt(Integer.toBinaryString(e)),
                        this::handleCollision,
                        TreeMap::new
                ));
    }

    public Map<MusicalGender, BigDecimal> getAverageRatingByMusicalGender(Collection<? extends Person> persons) {
        return persons.stream()
                .flatMap(person -> person.preferences.stream())
                .flatMap(preference -> preference.ratingByMusicalGenre.entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.collectingAndThen(Collectors.averagingDouble(Map.Entry::getValue),
                                averageInDouble -> new BigDecimal(averageInDouble, new MathContext(3)))));
    }

    private Integer handleCollision(Integer integer, Integer integer2) {
        System.out.println("Found duplicate key: " + integer);
        return integer;
    }

    static class Person {
        int age;
        String name;
        Collection<Preference> preferences;

        public Person(int age, String name, Collection<Preference> preferences) {
            this.age = age;
            this.name = name;
            this.preferences = preferences;
        }
    }

    static class Preference {
        Map<MusicalGender, Integer> ratingByMusicalGenre;

        public Preference(Map<MusicalGender, Integer> ratingByMusicalGenre) {
            this.ratingByMusicalGenre = ratingByMusicalGenre;
        }
    }

    static enum MusicalGender {
        ROCK,
        POP,
        COUNTRY,
        HIP_HOP
    }
}
