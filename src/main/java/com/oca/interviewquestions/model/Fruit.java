package com.oca.interviewquestions.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Model for fruits for a basic example
 */
public enum Fruit {
    BANANA, APPLE, ORANGE, PEAR;

    /**
     * Build a set of n fruits.
     *
     * @param elements the fruits
     * @return a {@link Set<Fruit>}
     */
    public static Set<Fruit> of(Fruit... elements) {
        return new HashSet<>(Arrays.asList(elements));
    }
}
