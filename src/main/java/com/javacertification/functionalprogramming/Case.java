package com.javacertification.functionalprogramming;

import lombok.NonNull;
import org.apache.commons.lang3.tuple.Pair;

import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.Arrays.stream;
import static org.apache.commons.lang3.ArrayUtils.nullToEmpty;

public class Case<T> {

    private final Pair<Supplier<Boolean>, Supplier<Result<T>>> pair;

    private Case(final Pair<Supplier<Boolean>, Supplier<Result<T>>> pair) {
        this.pair = pair;
    }

    private static <T> Predicate<Case<T>> getCondition() {
        return mCase -> mCase.pair.getLeft().get();
    }

    public Result<T> getValue() {
        return pair.getRight().get();
    }

    public static <T> Case<T> matchCase(@NonNull final Supplier<Boolean> condition,
                                        @NonNull final Supplier<Result<T>> value) {
        return new Case<>(Pair.of(condition, value));
    }

    public static <T> DefaultCase<T> matchCase(@NonNull final Supplier<Result<T>> value) {
        return DefaultCase.of(() -> true, value);
    }

    private static final class DefaultCase<T> extends Case<T> {

        private DefaultCase(Pair<Supplier<Boolean>, Supplier<Result<T>>> pair) {
            super(pair);
        }

        public static <T> DefaultCase<T> of(@NonNull final Supplier<Boolean> condition,
                                            @NonNull final Supplier<Result<T>> value) {
            return new DefaultCase<>(Pair.of(condition, value));
        }
    }

    @SafeVarargs
    public static <T> Result<T> match(@NonNull final DefaultCase<T> defaultCase,
                                      final Case<T>... matchers) {
        return stream((Case<T>[]) nullToEmpty(matchers))
                .filter(getCondition())
                .findFirst()
                .map(Case::getValue)
                .orElseGet(defaultCase::getValue);
    }
}
