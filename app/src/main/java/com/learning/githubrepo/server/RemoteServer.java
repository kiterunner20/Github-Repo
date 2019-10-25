package com.learning.githubrepo.server;

import com.learning.githubrepo.GithubRepoService;
import com.learning.githubrepo.core.Result;
import com.learning.githubrepo.mapper.GithubRepoMapper;
import com.learning.githubrepo.model.GithubRepoData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;


public class RemoteServer {

    private final GithubRepoService repoService;

    @Inject
    public RemoteServer(GithubRepoService repoService) {
        this.repoService = repoService;
    }

    public Single<Result<List<GithubRepoData>>> getRepoList(String searchQuery) {
        return repoService.getRepoList(searchQuery).map(new GithubRepoMapper());
    }
}
