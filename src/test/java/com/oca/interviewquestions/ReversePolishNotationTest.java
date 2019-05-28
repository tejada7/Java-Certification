package com.oca.interviewquestions;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReversePolishNotationTest {

    private Function<String, Integer> rpnInteger = new ReversePolishNotation<>(Integer.class);
    private Function<String, Float> rpnFloat = new ReversePolishNotation<>(Float.class);
    private Function<String, Double> rpnDouble = new ReversePolishNotation<>(Double.class);
    private Function<String, BigDecimal> rpnBigDecimal = new ReversePolishNotation<>(BigDecimal.class);

    @Test
    public void evaluate2Plus2IntegerTypes() {
        String input = "2 2 +";
        assertEquals(4, rpnInteger.apply(input).intValue());
    }

    @Test
    public void evaluateSimpleExpressionFloat() {
        //(1+6)*4
        String input = "1f 6f + 4f *";
        assertEquals(28f, rpnFloat.apply(input).floatValue());
    }

    @Test
    public void evaluateStandardExpressionDouble() {
        //((2+1)*3-5)/2
        String input = "2d 1d + 3d * 5d - 2d /";
        assertEquals(2d, rpnDouble.apply(input).doubleValue());
    }

    @Test
    public void evaluateComplexExpressionBigDecimal() {
        //((3+5)/4+(14-21)*4)/2
        String input = "3 5 + 4 / 14 21 - 4 * + 2 /";
        assertEquals(new BigDecimal(-13), rpnBigDecimal.apply(input));
    }
}
