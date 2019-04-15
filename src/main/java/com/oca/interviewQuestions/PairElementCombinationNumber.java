package com.oca.interviewQuestions;


import java.util.Collection;
import java.util.function.Function;

/**
 * Obtain the number of pair combinations given a set of Objects of type {@link T}.
 * This uses the formula n*(n-1)/2.
 *
 * @param <T> the generic type of the object
 */
class PairElementCombinationNumber<T> implements Function<Collection<T>, Integer> {

    @Override
    public Integer apply(Collection<T> elements) {
        final int n = elements.size();
        return (n * (n - 1)) / 2;
    }

}

