package com.learning.githubrepo.ui.favorite.binder;

import com.ahamed.multiviewadapter.DataListManager;
import com.ahamed.multiviewadapter.RecyclerAdapter;
import com.learning.githubrepo.model.GithubRepoData;
import com.learning.githubrepo.model.db.FavoriteRepo;

import java.util.ArrayList;


public class FavoriteRepoAdapter extends RecyclerAdapter {

    private DataListManager<FavoriteRepo> dataListManager;

    public FavoriteRepoAdapter(ItemSelectedListener listener) {

        dataListManager = new DataListManager<>(this);

        addDataManager(dataListManager);

        registerBinder(new FavoriteRepoBinder(listener));
    }

    public void setData(ArrayList<FavoriteRepo> repoList) {
        dataListManager.set(repoList);
    }

    public interface ItemSelectedListener {
        void onItemSelected(FavoriteRepo response);

        void onFavClicked(FavoriteRepo item);
    }
}

