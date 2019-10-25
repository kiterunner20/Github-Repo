package com.learning.githubrepo.model.db;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class FavoriteRepo implements Parcelable {

    public static FavoriteRepo create(int id, String name, String owner, String url, String description,
                                      int starCount, String language) {
        return new AutoValue_FavoriteRepo(id, name, owner, url, description, starCount, language);
    }

    public abstract int id();

    public abstract String name();

    public abstract String owner();

    public abstract String url();

    public abstract String description();

    public abstract int starCount();

    public abstract String language();
}
