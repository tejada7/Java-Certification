package com.javacertification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatsCalculator {

    public String calculate(int[] integers) {
        final var stats = Arrays.stream(integers)
                .summaryStatistics();
        class Joder {

        }
        class Toto extends Joder {

        }
        ArrayList<String> charSequences = null;
        final List<? super String> charSequences1 = doit(charSequences);
        return """
                minimum value = %d\
                maximum value = %d\
                number of elements in the sequence = %s\
                average value = %f
                """.formatted(stats.getMin(),
                stats.getMax(),
                integers.length,
                stats.getAverage());
    }

    static <E extends CharSequence> List<? super E> doit(List<E> nums) {
        return nums;
    }
}
