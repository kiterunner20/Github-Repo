package com.learning.githubrepo.ui.searchrepo;

import com.learning.githubrepo.api.GithubRepoApi;
import com.learning.githubrepo.core.BasePresenter;

import javax.inject.Inject;


public class GithubRepoPresenter extends BasePresenter {

    private final GithubRepoApi githubRepoApi;

    @Inject
    public GithubRepoPresenter(GithubRepoApi githubRepoApi) {
        this.githubRepoApi = githubRepoApi;
    }


}
