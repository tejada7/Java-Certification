package com.javacertification.ocp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ReferenceMethods {

    public static void main(String[] args) {
        // This is kind of tricky:
        BiPredicate<String, String> predicate = String::startsWith;
        // In fact, as this is a method reference at instance level and the BiPredicate takes two arguments,
        // from which the first one corresponds to the string instance to where the predicate test is applied, the
        // second corresponds to the startsWith argument of the method public boolean startsWith(String prefix)
        // The point here is that Java infers which Predicate argument applies where based on the type.

        // In order to test:
        String originalString = "value";
        assert !predicate.test(originalString, "V");

        // and
        assert predicate.test(originalString, "v");
    }
}
