package com.javacertification.puzzles.javacodechallenges;

import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

public class IsEven<T extends Number> implements Predicate<T> {

    @Override
    public boolean test(final T number) {
        return (requireNonNull(number, "number must be present.").intValue() & 1) == 0;
    }
}
