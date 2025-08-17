package com.javacertification;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.*;
import java.nio.file.Files;
import java.rmi.AccessException;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.HOURS;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.DisplayNameGenerator.IndicativeSentences;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;

@DisplayName("An status calculator")
@DisplayNameGeneration(IndicativeSentences.class)
@IndicativeSentencesGeneration(separator = " ", generator = ReplaceUnderscores.class)
class StatsCalculatorTest {

    @Test
    void should_calculate_stats_for_a_bunch_of_integers() {
        // Given
        final int[] integers = new int[]{6, 9, 15, -2, 92, 11};

        // When
        final String actual = new StatsCalculator().calculate(integers);

        // Then
        then(actual).isEqualTo("""
            minimum value = -2\
            maximum value = 92\
            number of elements in the sequence = 6\
            average value = 21.833333
            """);
    }

    @Test
    void name() {
        final var ld1 = LocalDateTime.of(2022, Month.NOVEMBER, 6, 2, 0);
        final var zd1 = ZonedDateTime.of(ld1, ZoneId.of("US/Eastern")); //
        final var ld2 = LocalDateTime.of(2022, Month.NOVEMBER, 6, 1, 0);
        final var zd2 = ZonedDateTime.of(ld2, ZoneId.of("US/Eastern"));
        System.out.println(zd1);
        System.out.println(zd1.plus(1, HOURS));
        System.out.println(zd2);
        System.out.println(HOURS.between(zd1, zd2));
    }

    enum Tota {
        CON;

        String name;

        Tota() {
//            System.out.println(i);
            doSm();
        }

        public static void doSm() {
        }

        public String getName() {
//            System.out.println(i);
            return name;
        }
    }

    non-sealed class MYClass implements Hdp, Hdp1 {

        @Override
        public String getId() {

            return null;
        }
    }

    sealed interface Mother {

        default String getId() {
            return "";
        }

        private static void toto() {
        }

        static void main(String[] args) throws Exception {
            final var mother = List.of(1, 2, 3);
            final var child = mother.subList(0, 2);
//            child.add(4);
            System.out.println(mother);
            System.out.println(child);
            Mother[] mothers = new Mother[1];
            Hdp[] hdps = new Hdp[2];
            mothers = hdps;
            Stream.of(1, 2).max(Integer::compare);
            AtomicInteger atomicInteger = new AtomicInteger();
            final var i = 'a' | 'b';
            Deque x = new ArrayDeque();
//            atomicInteger.add()

            char[] array7 = {'1', '2', '3'};
            char[] array8 = {'1', '2', '3'};

            System.out.println(Arrays.mismatch(array7, array8));

            Instant ins = Instant.parse("2024-06-25T06:43:30.000+02:00");
            System.out.println(ins.plus(-10, ChronoUnit.HOURS));
        }

        static void looper() throws Exception {
            var x = 0;
            while ((x = getX()) != 0) {
                for (int m = 10; m >= 0; m--) {
                    x = m;
                }
            }
        }

        static int getX() {
            return 5;
        }

        public static void m1() throws Exception {
            throw new Exception();
        }
    }

    sealed interface Hdp extends Mother {
        String TOTO = "";

        String getId();

        private void doS() {
            final var x = Mother.super.getId();
        }
    }

    interface Hdp1 {
        String TOTO = "";

        String getId();
    }


    //    @FunctionalInterface
    class MyFilter implements Predicate<String> {

        public boolean test(String input) {
            return input.equals("");
        }
    }

    record Pair<X, Y>(X left, Y right) {

    }
}
