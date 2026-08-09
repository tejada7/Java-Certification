package com.javacertification.gatherers;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Gatherer;

class GathererExamples {

    // Gatherer<T, A, R>
    //  -> T type of the element it consumes,
    //  -> A mutable state type
    //  -> R type of the element the gatherer is gonna produce

    public static Gatherer<String, Void, String> getToUpperCase() {
        return Gatherer.of(
            (_, element, downstream) -> {
                return downstream.push(element.toUpperCase());
            }
        );
    }

    public static <T> Gatherer<T, ?, T> distinct() {
        return Gatherer.<T, HashSet<? super T>, T>ofSequential(
            HashSet::new,
            (set, element, downstream) ->
                !set.add(element) || downstream.push(element)
        );
    }

    public static <T> Gatherer<T, Set<T>, T> sort() {
        return Gatherer.ofSequential(
            TreeSet::new,
            (set, element, _) -> {
                set.add(element);
                return true;
            },
            (set, downstream) -> {
                set.forEach(downstream::push);
            }
        );
    }
}
