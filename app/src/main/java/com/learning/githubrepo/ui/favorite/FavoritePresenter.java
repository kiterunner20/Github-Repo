package com.learning.githubrepo.ui.favorite;

import com.learning.githubrepo.api.GithubRepoApi;
import com.learning.githubrepo.core.BasePresenter;
import com.learning.githubrepo.core.ErrorAction;
import com.learning.githubrepo.model.db.FavoriteRepo;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FavoritePresenter extends BasePresenter<FavoriteView> {

    private final GithubRepoApi githubRepoApi;

    @Inject
    public FavoritePresenter(GithubRepoApi githubRepoApi) {
        this.githubRepoApi = githubRepoApi;
    }

    public void getCachedRepos() {
        showProgress();
        Disposable disposable =
                githubRepoApi.getCachedRepos()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(cachedRepoList -> {
                            if (isViewAttached()) {
                                showContent();
                                if (cachedRepoList.data() != null && cachedRepoList.data().size() > 0) {
                                    view.showCachedRepos(cachedRepoList.data());
                                } else {
                                    view.showEmpty("You have no favorite repositories");
                                }
                            }
                        }, new ErrorAction() {
                            @Override
                            protected void handleError(Throwable throwable) {
                                if (showContent()) {
                                    view.showError("No data found !!");
                                }
                            }
                        });
        addToSubscription(disposable);
    }

    public void deleteFromCache(FavoriteRepo favoriteRepo) {
        Single.just(1).map(integer -> {
            githubRepoApi.deleteFromCache(favoriteRepo);
            return integer;
        }).subscribeOn(Schedulers.io()).subscribe();
    }

}
