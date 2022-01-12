package com.javacertification.functionalprogramming;

import java.util.function.Consumer;

public interface Result<T> {

    void bind(final Consumer<T> success, final Consumer<T> failure);

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
    }

    record Failure<T>(T error) implements Result<T> {
        @Override
        public void bind(final Consumer<T> success, final Consumer<T> failure) {
            failure.accept(error);
        }
    }
}
