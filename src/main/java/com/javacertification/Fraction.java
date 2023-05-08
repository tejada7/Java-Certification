package com.javacertification;

import org.apache.commons.lang3.tuple.Pair;

import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

public record Fraction(int integer, Decimal decimal) {

    public static Fraction of(final int integer, final String decimalAsString) {
        final var split = decimalAsString.split("/");
        return new Fraction(integer, new Decimal(
                Integer.parseInt(split[0]), Integer.parseInt(split[1])));
    }

    public static Fraction of(final String decimalAsString) {
        return of(0, decimalAsString);
    }

    public static BiFunction<Fraction, Fraction, Fraction> add() {
        return (first, second) -> {
            final var numerator1 = first.decimal.numerator();
            final var numerator2 = second.decimal.numerator();

            final var denominator1 = first.decimal.denominator();
            final var denominator2 = second.decimal.denominator();
            final int commonDenominator = resolveCommonDenominator(denominator1, denominator2);

            final var partialSum1 = commonDenominator / denominator1 * numerator1;
            final var partialSum2 = commonDenominator / denominator2 * numerator2;

            final var finalNumerator = partialSum1 + partialSum2;
            final var partialDecimal = new Decimal(finalNumerator, commonDenominator);

            return new Fraction(0, partialDecimal);
        };
    }

    private static int resolveCommonDenominator(final int denominator1, final int denominator2) {
        final var maxDenominator = Math.max(denominator1, denominator2);
        return maxDenominator % Math.min(denominator1, denominator2) == 0
                ? maxDenominator
                : denominator1 * denominator2;
    }

    public static UnaryOperator<Fraction> simplify() {
        return fraction -> {
            final var numerator = fraction.decimal().numerator();
            final var denominator = fraction.decimal().denominator();
            final var maxMinPair = Pair.of(Math.max(numerator, denominator), Math.min(numerator, denominator));
            final var quotient = maxMinPair.getLeft() / maxMinPair.getRight();
            final var remainder = maxMinPair.getLeft() % maxMinPair.getRight();
            return quotient >= 1
                    ? new Fraction(fraction.integer() + quotient, new Decimal(remainder, denominator))
                    : fraction;
        };
    }

    public record Decimal(int numerator, int denominator) {

    }
}
