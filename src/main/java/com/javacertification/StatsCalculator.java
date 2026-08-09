package com.javacertification;

import java.util.Arrays;
import java.util.Locale;

public class StatsCalculator {

    public String calculate(int[] integers) {
        final var stats = Arrays.stream(integers)
            .summaryStatistics();
        Locale.setDefault(Locale.US);
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
}
