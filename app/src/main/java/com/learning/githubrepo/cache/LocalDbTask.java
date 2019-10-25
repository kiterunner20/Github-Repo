package com.learning.githubrepo.cache;

import com.learning.githubrepo.core.Result;
import com.learning.githubrepo.local.FavoriteRepoDatabase;
import com.learning.githubrepo.local.FavoriteRepoEntity;
import com.learning.githubrepo.mapper.CommonMapper;
import com.learning.githubrepo.mapper.FavoriteRepoMapper;
import com.learning.githubrepo.model.db.FavoriteRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


public class LocalDbTask {

    private final FavoriteRepoDatabase database;

    @Inject
    public LocalDbTask(FavoriteRepoDatabase database) {
        this.database = database;
    }

    public void insertToDb(FavoriteRepo favoriteRepo) {
        database.favoriteRepoDao().insert(new FavoriteRepoEntity(favoriteRepo.id(), favoriteRepo.name(),
                favoriteRepo.owner(), favoriteRepo.url(), favoriteRepo.description(), favoriteRepo.starCount(),
                favoriteRepo.language(), favoriteRepo.status()));
    }

    public Observable<Result<List<FavoriteRepo>>> getNonKotlinRepos() {
        return database.favoriteRepoDao().getAll().map(new CommonMapper());
    }

    public Observable<Result<List<FavoriteRepo>>> getKotlinRepos() {
        return database.favoriteRepoDao().getKotlinRepos().map(new CommonMapper());
    }

    public Observable<Result<List<FavoriteRepo>>> getCachedRepos() {
        return Observable.zip(getKotlinRepos(), getNonKotlinRepos(), new FavoriteRepoMapper());
    }


    public void deleteFromCache(FavoriteRepo favoriteRepo) {
        database.favoriteRepoDao().delete(new FavoriteRepoEntity(favoriteRepo.id(), favoriteRepo.name(),
                favoriteRepo.owner(), favoriteRepo.url(), favoriteRepo.description(), favoriteRepo.starCount(),
                favoriteRepo.language(), favoriteRepo.status()));
    }
}
