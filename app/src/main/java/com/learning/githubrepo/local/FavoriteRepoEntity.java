package com.learning.githubrepo.local;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FavoriteRepoEntity {

    public static final String GITHUB_ID = "github_id";
    public static final String REPO_NAME = "repo_name";
    public static final String OWNER = "repo_owner";
    public static final String URL = "repo_url";
    public static final String DESCRIPTION = "repo_description";
    public static final String STARS = "repo_stars";
    public static final String LANGUAGE = "repo_language";

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = GITHUB_ID)
    public int githubID;

    @ColumnInfo(name = REPO_NAME)
    public String name;

    @ColumnInfo(name = OWNER)
    public String owner;

    @ColumnInfo(name = URL)
    public String url;

    @ColumnInfo(name = DESCRIPTION)
    public String description;

    @ColumnInfo(name = STARS)
    public int stars;

    @ColumnInfo(name = LANGUAGE)
    public String language;

    public FavoriteRepoEntity(int githubID, String name, String owner, String url, String description,
                              int stars, String language) {
        this.githubID = githubID;
        this.name = name;
        this.owner = owner;
        this.url = url;
        this.description = description;
        this.stars = stars;
        this.language = language;
    }

}
