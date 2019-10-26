package com.learning.githubrepo.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import io.reactivex.Observable;


@Dao
public abstract class FavoriteRepoDao {
    @Transaction
    public boolean upsert(FavoriteRepoEntity favoriteRepoEntity) {
        insert(favoriteRepoEntity);
        delete(favoriteRepoEntity.githubID);
        return true;
    }

    @Insert
    public abstract void insert(FavoriteRepoEntity favoriteRepoEntity);


    @Query("SELECT * FROM FavoriteRepoEntity WHERE repo_language != 'Kotlin' ORDER BY id DESC")
    public abstract Observable<List<FavoriteRepoEntity>> getAll();

    @Query("SELECT * FROM FavoriteRepoEntity WHERE repo_language = 'Kotlin' ORDER BY id DESC ")
    public abstract Observable<List<FavoriteRepoEntity>> getKotlinRepos();

    @Query("DELETE FROM FavoriteRepoEntity WHERE github_id = :id")
    public abstract void delete(long id);

    @Delete
    public abstract void delete(FavoriteRepoEntity favoriteRepoEntity);


}
