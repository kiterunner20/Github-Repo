package com.learning.githubrepo.core;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Error {

    public static Error create(String message, boolean canRetry) {
        return new AutoValue_Error(message, canRetry);
    }

    abstract String message();

    abstract boolean canRetry();
}