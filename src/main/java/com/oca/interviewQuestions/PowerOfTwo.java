package com.oca.interviewQuestions;

import java.util.function.Function;

public class PowerOfTwo implements Function<Integer, Boolean> {
    @Override
    public Boolean apply(Integer number) {
        return NumberUtils.isPowerOf(number, 2);
    }
}
