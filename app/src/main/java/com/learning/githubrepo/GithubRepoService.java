package com.learning.githubrepo;

import com.learning.githubrepo.data.GithubReposiotry;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubRepoService {

    @GET("search/repositories")
    Single<GithubReposiotry> getRepoList(@Query("q") String searchQuery);
}
