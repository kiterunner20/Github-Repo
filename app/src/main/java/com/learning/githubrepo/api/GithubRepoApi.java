package com.learning.githubrepo.api;

import com.learning.githubrepo.server.RemoteServer;

import javax.inject.Inject;

public class GithubRepoApi {

    private final RemoteServer remoteServer;

    @Inject
    public GithubRepoApi(RemoteServer remoteServer) {
        this.remoteServer = remoteServer;
    }
}
