package com.learning.githubrepo.mapper;

import com.learning.githubrepo.core.Result;
import com.learning.githubrepo.local.FavoriteRepoEntity;
import com.learning.githubrepo.model.db.FavoriteRepo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;

public class CommonMapper implements Function<List<FavoriteRepoEntity>, Result<List<FavoriteRepo>>> {
    @Override
    public Result<List<FavoriteRepo>> apply(List<FavoriteRepoEntity> repoEntities) throws Exception {
        List<FavoriteRepo> favoriteRepoList = new ArrayList<>();

        if (repoEntities != null) {

            for (int i = 0; i < repoEntities.size(); i++) {

                favoriteRepoList.add(FavoriteRepo.create(repoEntities.get(i).githubID,
                        repoEntities.get(i).name, repoEntities.get(i).owner,
                        repoEntities.get(i).url, repoEntities.get(i).description, repoEntities.get(i).stars,
                        repoEntities.get(i).language));

            }
            return Result.dataState(favoriteRepoList);

        } else {
            return Result.emptyState("No data found", false);
        }
    }
}

