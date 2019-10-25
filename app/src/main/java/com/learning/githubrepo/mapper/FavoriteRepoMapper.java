package com.learning.githubrepo.mapper;

import com.learning.githubrepo.core.Result;
import com.learning.githubrepo.model.db.FavoriteRepo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.BiFunction;

public class FavoriteRepoMapper implements BiFunction<Result<List<FavoriteRepo>>, Result<List<FavoriteRepo>>, Result<List<FavoriteRepo>>> {
    @Override
    public Result<List<FavoriteRepo>> apply(Result<List<FavoriteRepo>> kotlinRepo, Result<List<FavoriteRepo>> nonKotlinRepo) throws Exception {

        List<FavoriteRepo> favoriteRepos = new ArrayList<>();

        if (kotlinRepo != null) {
            if (kotlinRepo.data() != null && nonKotlinRepo.data().size() > 0) {
                for (int i = 0; i < kotlinRepo.data().size(); i++) {

                    favoriteRepos.add(FavoriteRepo.create(kotlinRepo.data().get(i).id(),
                            kotlinRepo.data().get(i).name(), kotlinRepo.data().get(i).owner(),
                            kotlinRepo.data().get(i).url(), kotlinRepo.data().get(i).description(),
                            kotlinRepo.data().get(i).starCount(), kotlinRepo.data().get(i).language()));
                }
            }
        }
        if (nonKotlinRepo != null) {
            if (nonKotlinRepo.data() != null && nonKotlinRepo.data().size() > 0) {
                for (int i = 0; i < nonKotlinRepo.data().size(); i++) {

                    favoriteRepos.add(FavoriteRepo.create(nonKotlinRepo.data().get(i).id(),
                            nonKotlinRepo.data().get(i).name(), nonKotlinRepo.data().get(i).owner(),
                            nonKotlinRepo.data().get(i).url(), nonKotlinRepo.data().get(i).description(),
                            nonKotlinRepo.data().get(i).starCount(), nonKotlinRepo.data().get(i).language()));
                }
            }
        }

        return Result.dataState(favoriteRepos);
    }
}
