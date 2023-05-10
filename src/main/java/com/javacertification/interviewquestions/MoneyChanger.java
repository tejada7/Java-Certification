package com.javacertification.interviewquestions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class MoneyChanger {

    MoneyChange calculateChange(final BigDecimal originalPrice, final BigDecimal paidWith) {
        final var effectiveChange = paidWith.subtract(originalPrice);
        if (effectiveChange.equals(BigDecimal.ZERO)) {
            return new NoChange();
        } else if (effectiveChange.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalStateException("Paid with argument does not cover the actual price.");
        } else {
            var temp = effectiveChange;
            final Map<CashOptions, Integer> result = new EnumMap<>(CashOptions.class);
            final var cashOrderedOptions = CashOptions.values();
            var index = 0;
            while (temp.compareTo(BigDecimal.ZERO) > 0) {
                var optionEvaluated = cashOrderedOptions[index++];
                var quotient = temp.toBigInteger().divide(optionEvaluated.getValue().toBigInteger());
                if (quotient.compareTo(BigInteger.ZERO) > 0) {
                    result.put(optionEvaluated, quotient.intValue());
                    temp = temp.subtract(BigDecimal.valueOf(optionEvaluated.getValue().longValue() * quotient.longValue()));
                }
            }
            return new ExistingMoneyChange(result);
        }
    }

    public enum CashOptions {
        FIFTY(BigDecimal.valueOf(50)),
        TWENTY(BigDecimal.valueOf(20)),
        TEN(BigDecimal.valueOf(10)),
        FIVE(BigDecimal.valueOf(5)),
        TWO(BigDecimal.valueOf(2)),
        ONE(BigDecimal.valueOf(1));

        private final BigDecimal value;

        CashOptions(final BigDecimal value) {
            this.value = value;
        }

        public BigDecimal getValue() {
            return value;
        }
    }

    record ExistingMoneyChange(Map<CashOptions, Integer> cashOptionsCounter) implements MoneyChange {

        public static ExistingMoneyChange of(CashOptions... cashOptions) {
            return Stream.of(cashOptions)
                    // .collect(collectingAndThen(groupingBy(identity(), collectingAndThen(counting(), Long::intValue)),
                    .collect(collectingAndThen(groupingBy(Function.identity(), summingInt(x -> 1)),
                                               ExistingMoneyChange::new));
        }
    }

    record NoChange() implements MoneyChange {
    }

    sealed interface MoneyChange permits ExistingMoneyChange, NoChange {

        default Collector<CashOptions, ?, MoneyChange> toMoneyChange() {
            return collectingAndThen(toUnmodifiableList(), this::toMoneyChange);
        }

        default MoneyChange toMoneyChange(final Collection<CashOptions> cashOptions) {
            return cashOptions.isEmpty()
                    ? new NoChange()
                    : ExistingMoneyChange.of(cashOptions.toArray(CashOptions[]::new));
        }
    }
}
