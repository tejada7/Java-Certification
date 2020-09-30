package com.javacertification.interviewquestions;


import java.util.*;
import java.util.function.Function;

/**
 * Obtain the pair combinations given a set of Objects of type {@link T}.
 *
 * @param <T> the generic type of the object
 */
public class GetPossiblePairCombinations<T> implements Function<List<T>, List<Set<T>>> {

    @Override
    public List<Set<T>> apply(List<T> elements) {
        List<Set<T>> result = new ArrayList<>();
        for (int i = 0; i < elements.size() - 1; i++) {
            for (int j = i + 1; j < elements.size(); j++) {
                result.add(of(elements.get(i), elements.get(j)));
            }
        }
        return result;
    }

    @SafeVarargs
    private final Set<T> of(T... elements) {
        return new HashSet<>(Arrays.asList(elements));
    }
}

