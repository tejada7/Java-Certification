package com.javacertification.interviewquestions.model;

import com.javacertification.interviewquestions.GetPossiblePairCombinations;
import com.javacertification.interviewquestions.GetPossiblePairCombinationsSize;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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

    /**
     * Get the number of possible pair combination given a set of fruits.
     *
     * @param fruits the data set
     *
     * @return an <code>int<code/> value that represents the number of pair combinations from the data set
     */
    public static int getFruitPairCombinationsNumber(Set<Fruit> fruits) {
        return new GetPossiblePairCombinationsSize<Fruit>().applyAsInt(fruits);
    }

    /**
     * Get the set of possible pair combination given a set of fruits.
     *
     * @param fruits the data set
     *
     * @return an {@link List <Set<Fruit>>} object that contains the pair combinations from the data set
     */
    public static List<Set<Fruit>> getFruitPairCombinationsElements(List<Fruit> fruits) {
        return new GetPossiblePairCombinations<Fruit>().apply(fruits);
    }
}
