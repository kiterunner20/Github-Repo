package com.learning.githubrepo.di.component;

import com.learning.githubrepo.di.module.GithubRepoModule;
import com.learning.githubrepo.ui.favorite.FavoriteFragment;
import com.learning.githubrepo.ui.searchrepo.GithubRepoSearchFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = GithubRepoModule.class)

public interface GithubRepoComponent {
    void inject(GithubRepoSearchFragment githubRepoSearchFragment);

    void inject(FavoriteFragment favoriteFragment);


}
