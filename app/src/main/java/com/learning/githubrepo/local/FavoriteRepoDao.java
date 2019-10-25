package com.learning.githubrepo.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Observable;


@Dao
public interface FavoriteRepoDao {


    @Insert
    void insert(FavoriteRepoEntity favoriteRepoEntity);

    @Query("SELECT * FROM FavoriteRepoEntity WHERE repo_language != 'Kotlin' ORDER BY id DESC")
    Observable<List<FavoriteRepoEntity>> getAll();

    @Query("SELECT * FROM FavoriteRepoEntity WHERE repo_language = 'Kotlin' ORDER BY id DESC ")
    Observable<List<FavoriteRepoEntity>> getKotlinRepos();

    @Delete
    void delete(FavoriteRepoEntity favoriteRepoEntity);


}
