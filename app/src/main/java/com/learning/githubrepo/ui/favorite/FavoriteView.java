package com.learning.githubrepo.ui.favorite;

import com.learning.githubrepo.core.BaseView;
import com.learning.githubrepo.model.db.FavoriteRepo;

import java.util.List;

public interface FavoriteView extends BaseView {
    void showCachedRepos(List<FavoriteRepo> data);
}
