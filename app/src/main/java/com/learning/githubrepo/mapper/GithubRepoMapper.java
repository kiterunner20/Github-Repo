package com.learning.githubrepo.mapper;

import com.learning.githubrepo.core.Result;
import com.learning.githubrepo.data.GithubReposiotry;
import com.learning.githubrepo.data.Item;
import com.learning.githubrepo.model.GithubRepoData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;

public class GithubRepoMapper implements Function<GithubReposiotry, Result<List<GithubRepoData>>> {
    @Override
    public Result<List<GithubRepoData>> apply(GithubReposiotry githubReposiotry) throws Exception {
        List<GithubRepoData> githubRepoData = new ArrayList<>();

        if (githubReposiotry != null) {

            if (githubReposiotry.getItems() != null && githubReposiotry.getItems().size() > 0) {
                for (Item item : githubReposiotry.getItems()) {
                    String owner = "";
                    if (item.getOwner() != null) {
                        if (item.getOwner().getLogin() != null) {
                            owner = item.getOwner().getLogin();
                        }
                    }

                    githubRepoData.add(GithubRepoData.create(item.getId(), item.getName(),
                            owner, item.getHtmlUrl(), item.getDescription(), item.getStargazersCount(),
                            item.getLanguage()));
                }
                return Result.dataState(githubRepoData);
            } else {
                return Result.errorState("No data found", false);
            }

        } else {
            return Result.errorState("No data found", false);

        }
    }
}
