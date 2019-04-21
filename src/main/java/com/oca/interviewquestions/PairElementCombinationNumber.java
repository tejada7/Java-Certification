package com.oca.interviewquestions;


import java.util.Collection;
import java.util.function.ToIntFunction;

/**
 * Obtain the number of pair combinations given a set of Objects of type {@link T}.
 * This uses the formula n*(n-1)/2.
 *
 * @param <T> the generic type of the object
 */
public class PairElementCombinationNumber<T> implements ToIntFunction<Collection<T>> {

    @Override
    public int applyAsInt(Collection<T> elements) {
        final int n = elements.size();
        return (n * (n - 1)) / 2;
    }
}

