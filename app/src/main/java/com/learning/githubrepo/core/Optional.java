package com.learning.githubrepo.core;

import androidx.annotation.Nullable;

public final class Optional<T> {

    private final T value;

    private Optional(@Nullable T value) {
        this.value = value;
    }

    public static <M> Optional<M> data(@Nullable M value) {
        return new Optional<>(value);
    }

    public static <M> Optional<M> empty() {
        return new Optional<>(null);
    }

    public T get() {
        if (value == null) {
            throw new IllegalStateException("Optional is null. Check before accessing the value!");
        }
        return value;
    }

    public boolean isEmpty() {
        return value == null;
    }

    public boolean notEmpty() {
        return value != null;
    }
}
