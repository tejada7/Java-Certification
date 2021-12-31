package com.javacertification.interviewquestions;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public final class FizzBuzz implements Function<FizzBuzz.Input, List<String>> {

    @Override
    public List<String> apply(final Input input) {
        return IntStream.rangeClosed(input.from, input.to)
                .boxed()
                .map(this::applyFizzBuzzRules)
                .toList();

    }

    private String applyFizzBuzzRules(final Integer input) {
        if (input % 3 == 0 && input % 5 == 0) {
            return "FizzBuzz";
        } else if (input % 3 == 0) {
            return "Fizz";
        } else if (input % 5 == 0) {
            return "Buzz";
        } else {
            return String.valueOf(input);
        }
    }

    public static class Input {
        private final int from;
        private final int to;

        public static Input of(final int from, final int to) {
            return new Input(from, to);
        }

        public Input(int from, int to) {
            if (from > to) {
                throw new IllegalArgumentException();
            }
            this.from = from;
            this.to = to;
        }
    }
}

