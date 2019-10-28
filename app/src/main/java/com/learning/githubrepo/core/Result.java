package com.learning.githubrepo.core;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Result<T> {

    public static <T> Result<T> dataState(T data) {
        return create(data, null, null);
    }

    public static <T> Result<T> emptyState(String message, boolean canRetry) {
        return create(null, Error.create(message, canRetry), null);
    }

    public static <T> Result<T> errorState(String message, boolean canRetry) {
        return create(null, null, Error.create(message, canRetry));
    }

    private static <T> Result<T> create(T data, Error empty, Error error) {
        return new AutoValue_Result<>(Optional.data(data), Optional.data(empty), Optional.data(error));
    }

    public boolean isSuccess() {
        return !value().isEmpty();
    }

    public boolean isEmpty() {
        return !emptyState().isEmpty();
    }

    public boolean isError() {
        return !errorState().isEmpty();
    }

    public T data() {
        return value().get();
    }

    public String emptyMessage() {
        return emptyState().get().message();
    }

    public String errorMessage() {
        return errorState().get().message();
    }

    public boolean canRetry() {
        return errorState().get().canRetry();
    }

    abstract Optional<T> value();

    abstract Optional<Error> emptyState();

    abstract Optional<Error> errorState();
}

