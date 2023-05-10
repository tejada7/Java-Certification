package com.javacertification.interviewquestions;

import com.javacertification.interviewquestions.MoneyChanger.MoneyChange;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.javacertification.interviewquestions.MoneyChanger.CashOptions;
import static com.javacertification.interviewquestions.MoneyChanger.ExistingMoneyChange;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenIllegalStateException;
import static org.junit.jupiter.api.DisplayNameGenerator.IndicativeSentences;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;

@DisplayName("Money change")
@DisplayNameGeneration(IndicativeSentences.class)
@IndicativeSentencesGeneration(separator = " ", generator = ReplaceUnderscores.class)
class ExistingMoneyChangerTest {

    private final MoneyChanger moneyChanger = new MoneyChanger();

    private static Stream<Arguments> fixture() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(2),
                             BigDecimal.valueOf(20),
                             ExistingMoneyChange.of(CashOptions.TEN, CashOptions.FIVE,
                                                    CashOptions.TWO, CashOptions.ONE)),
                Arguments.of(BigDecimal.valueOf(2),
                             BigDecimal.valueOf(70),
                             ExistingMoneyChange.of(CashOptions.FIFTY, CashOptions.TEN,
                                                    CashOptions.FIVE, CashOptions.TWO, CashOptions.ONE)),
                Arguments.of(BigDecimal.valueOf(2),
                             BigDecimal.valueOf(200),
                             ExistingMoneyChange.of(CashOptions.FIFTY, CashOptions.FIFTY, CashOptions.FIFTY,
                                                    CashOptions.TWENTY, CashOptions.TWENTY, CashOptions.FIVE,
                                                    CashOptions.TWO, CashOptions.ONE)),
                Arguments.of(BigDecimal.valueOf(2),
                             BigDecimal.valueOf(2),
                             new MoneyChanger.NoChange()
                ));
    }

    @ParameterizedTest(name = "Given the original price: {0}, paid with: {1}, the expected change is: {2}")
    @MethodSource("fixture")
    void should_return_optimized_change(final BigDecimal originalPrice,
                                        final BigDecimal paidWith,
                                        final MoneyChange expected) {
        then(moneyChanger.calculateChange(originalPrice, paidWith)).isEqualTo(expected);
    }

    @Test
    void should_throw_exception_when_original_price_greater_than_paid_with() {
        thenIllegalStateException().isThrownBy(() -> moneyChanger.calculateChange(BigDecimal.ONE, BigDecimal.ZERO));
    }
}
