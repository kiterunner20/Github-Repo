package com.learning.githubrepo.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import io.reactivex.annotations.Nullable;

@AutoValue
public abstract class GithubRepoData implements Parcelable {

    public static GithubRepoData create(int id, String name, String owner, String url,
                                        String description, int starCount, String language, boolean status) {
        return new AutoValue_GithubRepoData(id, name, owner, url, description, starCount, language, status);
    }

    public abstract int id();

    @Nullable
    public abstract String name();

    @Nullable
    public abstract String owner();

    @Nullable
    public abstract String url();

    @Nullable
    public abstract String description();

    public abstract int starCount();

    @Nullable
    public abstract String language();

    public abstract boolean status();
}
