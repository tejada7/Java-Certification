package com.javacertification.ocp;

import java.util.List;
import java.util.function.BiPredicate;

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

    /**
     * COMPLEX EXAMPLE:
     */
    interface Carnivore {
        default int calories(List<String> foods) {
            return foods.size() * 100;
        }

        int eat(List<String> foods);
    }

    static class Tiger implements Carnivore {
        @Override
        public int eat(List<String> foods) {
            return foods.size();
        }
    }

    static class TestClass {

        public static int size(List<String> names) {
            return names.size();
        }

        public int calculate(List<String> names) {
            return 0;
        }

        public static void process(List<String> names, Carnivore carnivore) {
            carnivore.eat(names);
        }

        public static void main(String[] args) {
            var names = List.of("a", "b", "c");
            var tiger = new Tiger();
            var testClass = new TestClass();

            process(names, tiger::eat);
//            process(names, Tiger::eat); // This does not compile due to a missing reference to Tiger instance (as line above)
            process(names, tiger::calories);
            process(names, TestClass::size);
            process(names, testClass::calculate);
        }
    }
}
