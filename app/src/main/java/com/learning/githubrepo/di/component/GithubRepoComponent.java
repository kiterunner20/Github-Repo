package com.learning.githubrepo.di.component;

import com.learning.githubrepo.di.module.GithubRepoModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = GithubRepoModule.class)

public interface GithubRepoComponent {
}
