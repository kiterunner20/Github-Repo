package com.learning.githubrepo.server;

import com.learning.githubrepo.GithubRepoService;

import javax.inject.Inject;

public class RemoteServer {

    private final GithubRepoService repoService;

    @Inject
    public RemoteServer(GithubRepoService repoService) {
        this.repoService = repoService;
    }
}
