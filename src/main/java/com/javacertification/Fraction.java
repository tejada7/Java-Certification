package com.javacertification;

public record Fraction(int integer, Decimal decimal) {

    public static Fraction of(final int integer, final String decimalAsString) {
        final var split = decimalAsString.split("/");
        return new Fraction(integer, new Decimal(
                Integer.parseInt(split[0]), Integer.parseInt(split[1])));
    }

    public static Fraction of(final String decimalAsString) {
        return of(0, decimalAsString);
    }

    public Fraction add(final Fraction another) {
        final var numerator1 = decimal.numerator;
        final var numerator2 = another.decimal.numerator;

        final var denominator1 = decimal.denominator;
        final var denominator2 = another.decimal.denominator();
        final int commonDenominator = resolveCommonDenominator(denominator1, denominator2);

        final var partialSum1 = commonDenominator / denominator1 * numerator1;
        final var partialSum2 = commonDenominator / denominator2 * numerator2;

        final var finalNumerator = partialSum1 + partialSum2;
        final var partialDecimal = new Decimal(finalNumerator, commonDenominator);

        return new Fraction(0, partialDecimal);
    }

    private int resolveCommonDenominator(final int denominator1, final int denominator2) {
        final var maxDenominator = Math.max(denominator1, denominator2);
        return maxDenominator % Math.min(denominator1, denominator2) == 0
                ? maxDenominator
                : denominator1 * denominator2;
    }

    public record Decimal(int numerator, int denominator) {

    }
}
