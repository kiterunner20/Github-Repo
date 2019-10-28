package com.learning.githubrepo.api;

import com.learning.githubrepo.cache.LocalDbTask;
import com.learning.githubrepo.core.Result;
import com.learning.githubrepo.model.GithubRepoData;
import com.learning.githubrepo.model.db.FavoriteRepo;
import com.learning.githubrepo.server.RemoteServer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class GithubRepoApi {

    private final RemoteServer remoteServer;
    private final LocalDbTask localDbTask;

    @Inject
    public GithubRepoApi(RemoteServer remoteServer, LocalDbTask localDbTask) {
        this.remoteServer = remoteServer;
        this.localDbTask = localDbTask;
    }

    public Single<Result<List<GithubRepoData>>> getRepoList(String searchQuery) {
        return remoteServer.getRepoList(searchQuery);
    }

    public void insertToDb(FavoriteRepo favoriteRepo) {
        localDbTask.insertToDb(favoriteRepo);
    }

    public Observable<Result<List<FavoriteRepo>>> getCachedRepos() {
        return localDbTask.getCachedRepos();
    }

    public void deleteFromCache(FavoriteRepo favoriteRepo) {
        localDbTask.deleteFromCache(favoriteRepo);
    }
}
