package com.javacertification.functionalprogramming;

import java.util.Optional;
import java.util.function.Consumer;

public interface Result<T> {

    void bind(final Consumer<T> success, final Consumer<T> failure);

    default boolean isSuccess() {
        return this instanceof Success;
    }

    default boolean isFailure() {
        return this instanceof Failure;
    }

    Optional<T> getValue();

    Optional<T> getError();

    static Result<String> failure(final String message) {
        return new Failure<>(message);
    }

    static <T> Result<T> success(T value) {
        return new Success<>(value);
    }

    record Success<T>(T value) implements Result<T> {
        @Override
        public void bind(Consumer<T> success, Consumer<T> failure) {
            success.accept(value);
        }

        @Override
        public Optional<T> getValue() {
            return Optional.of(value);
        }

        @Override
        public Optional<T> getError() {
            return Optional.empty();
        }
    }

    record Failure<T>(T error) implements Result<T> {
        @Override
        public void bind(final Consumer<T> success, final Consumer<T> failure) {
            failure.accept(error);
        }

        @Override
        public Optional<T> getValue() {
            return Optional.empty();
        }

        @Override
        public Optional<T> getError() {
            return Optional.of(error);
        }

    }
}
