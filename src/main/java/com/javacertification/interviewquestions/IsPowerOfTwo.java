package com.javacertification.interviewquestions;

import java.util.function.IntPredicate;

/**
 * Implementation if a {@link IntPredicate} functional interface to determine whether a given number is power of 2.
 */
public class IsPowerOfTwo implements IntPredicate {

    @Override
    public boolean test(int value) {
        return IntUtils.isPowerOf(value, 2);
    }
}
