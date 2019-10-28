package com.learning.githubrepo.ui.searchrepo;

import com.learning.githubrepo.api.GithubRepoApi;
import com.learning.githubrepo.core.BasePresenter;
import com.learning.githubrepo.core.ErrorAction;
import com.learning.githubrepo.model.db.FavoriteRepo;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class GithubRepoPresenter extends BasePresenter<GithubRepoView> {

    private final GithubRepoApi githubRepoApi;

    @Inject
    public GithubRepoPresenter(GithubRepoApi githubRepoApi) {
        this.githubRepoApi = githubRepoApi;
    }


    public void getRepoList(String searchQuery) {
        showProgress();
        Disposable disposable =
                githubRepoApi.getRepoList(searchQuery)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(repoList -> {
                            if (isViewAttached()) {
                                showContent();
                                if (repoList.data() != null) {
                                    view.showRepos(repoList.data());
                                } else {
                                    view.showError(repoList.errorMessage());
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

    public void insertToDb(FavoriteRepo favoriteRepo) {
        Single.just(1).map(integer -> {
            githubRepoApi.insertToDb(favoriteRepo);
            return integer;
        }).subscribeOn(Schedulers.io()).subscribe();
    }

}
