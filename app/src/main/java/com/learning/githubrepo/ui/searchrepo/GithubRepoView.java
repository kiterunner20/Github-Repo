package com.learning.githubrepo.ui.searchrepo;

import com.learning.githubrepo.core.BaseView;
import com.learning.githubrepo.model.GithubRepoData;

import java.util.List;

interface GithubRepoView extends BaseView {

    void showRepos(List<GithubRepoData> data);

}
