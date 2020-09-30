package com.javacertification.miscellaneous;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GettingRidOfForEach {

    public static void main(String[] args) {
        // Calculates the inverse and sqrt of a set of random double numbers:
        classicalSolution();
        betterSolution();
    }

    private static void betterSolution() {
        Function<Double, Stream<Double>> flatMapper =
                d -> NewMath.inv(d)
                        .flatMap(NewMath::sqrt)
                        .map(Stream::of)
                        .orElseGet(Stream::empty);

        List<Double> rightResult =
                ThreadLocalRandom.current()
                        .doubles(10_000).parallel()
                        .map(d -> d * 20 - 10)
                        .boxed()
                        .flatMap(flatMapper)
                        .collect(Collectors.toList());
        System.out.println("# result = " + rightResult.size());
    }

    private static void classicalSolution() {
        List<Double> result = new ArrayList<>();
        ThreadLocalRandom.current()
                .doubles(10_000).boxed()/*.parallel()*/
                .forEach(
                        d -> NewMath.inv(d)
                                .ifPresent(
                                        inv -> NewMath.sqrt(inv)
                                                .ifPresent(
                                                        result::add
                                                )
                                )
                );
        System.out.println("# result = " + result.size());
    }

    private static class NewMath {

        private NewMath() {

        }

        private static Optional<Double> inv(Double d) {
            return (null == d || d == 0d) ? Optional.empty() :
                    Optional.of(1d / d);
        }

        private static Optional<Double> sqrt(Double d) {
            return (null == d || d < 0d) ? Optional.empty() :
                    Optional.of(Math.sqrt(d));
        }
    }
}
