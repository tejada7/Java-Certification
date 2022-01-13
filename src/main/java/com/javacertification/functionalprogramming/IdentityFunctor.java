package com.javacertification.functionalprogramming;

import lombok.NonNull;

import java.util.function.Consumer;
import java.util.function.Function;

public record IdentityFunctor<T>(@NonNull T value) {

    public static <T> IdentityFunctor<T> of(final T value) {
        return new IdentityFunctor<>(value);
    }

    public <R> IdentityFunctor<R> map(final Function<T, R> mappingFunction) {
        return new IdentityFunctor<>(mappingFunction.apply(value));
    }

    public <R> R transform(final Function<T, R> mappingFunction) {
        return mappingFunction.apply(value);
    }

    public void consume(final Consumer<T> consumer) {
        consumer.accept(value);
    }
}
