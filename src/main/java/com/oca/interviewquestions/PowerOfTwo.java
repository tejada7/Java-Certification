package com.oca.interviewquestions;

import java.util.function.IntPredicate;

public class PowerOfTwo implements IntPredicate {

    @Override
    public boolean test(int value) {
        return NumberUtils.isPowerOf(value, 2);
    }
}
