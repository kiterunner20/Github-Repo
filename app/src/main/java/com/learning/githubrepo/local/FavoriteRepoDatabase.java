package com.learning.githubrepo.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {FavoriteRepoEntity.class}, version = 2, exportSchema = false)
public abstract class FavoriteRepoDatabase extends RoomDatabase {

    public abstract FavoriteRepoDao favoriteRepoDao();
}
