package com.learning.githubrepo.api;

import com.learning.githubrepo.core.Result;
import com.learning.githubrepo.model.GithubRepoData;
import com.learning.githubrepo.server.RemoteServer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class GithubRepoApi {

    private final RemoteServer remoteServer;

    @Inject
    public GithubRepoApi(RemoteServer remoteServer) {
        this.remoteServer = remoteServer;
    }

    public Single<Result<List<GithubRepoData>>> getRepoList(String searchQuery) {
        return remoteServer.getRepoList(searchQuery);
    }
}
