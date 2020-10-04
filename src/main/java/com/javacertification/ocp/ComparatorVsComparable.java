package com.javacertification.ocp;

import java.math.BigDecimal;
import java.util.*;

public class ComparatorVsComparable {

    class Duck implements Comparable<Duck> {

        private final String name;
        private final BigDecimal weight;

        Duck(String name, BigDecimal weight) {
            this.name = name;
            this.weight = weight;
        }

        public String getName() {
            return name;
        }

        public BigDecimal getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Duck duck) {
            return name.compareTo(duck.name);
        }

        /**
         * It's a good practice to have consistency between the {@link #equals(Object)} and {@link #compareTo(Duck)} ()}
         * methods.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Duck duck = (Duck) o;
            return Objects.equals(name, duck.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Duck.class.getSimpleName() + "[", "]")
                    .add("name='" + name + "'")
                    .add("weight=" + weight)
                    .toString();
        }
    }

    public static void main(String[] args) {
        final Duck duck1 = new ComparatorVsComparable().new Duck("Donald", BigDecimal.valueOf(15));
        final Duck duck2 = new ComparatorVsComparable().new Duck("Erlan", BigDecimal.TEN);
        final Duck duck3 = new ComparatorVsComparable().new Duck("Donald", BigDecimal.valueOf(16));
        final List<Duck> ducks = Arrays.asList(duck1, duck2, duck3);

        // Using the default compare to method:
        assert duck1.compareTo(duck2) < 0;

        // Now let's say we want to compare ducks based on their weight, we create a customised comparator:
        Comparator<Duck> weightComparator = Comparator.comparing(Duck::getWeight);
        // Sorting the list:
        ducks.sort(weightComparator);
        assert ducks.get(0).equals(duck2);

        // Or we first order by name but if there is a tie we look at the weight in descending order:
        Comparator<Duck> combinedComparator = Comparator.comparing(Duck::getName)
                .thenComparing(Duck::getWeight)
                .reversed();
        // Sorting the list
        ducks.sort(combinedComparator);
        assert ducks.get(2).equals(duck3);

        // Or using the default Duck Comparable implementation:
        ducks.sort(Comparator.naturalOrder());
        assert ducks.get(0).equals(duck1);
    }
}
