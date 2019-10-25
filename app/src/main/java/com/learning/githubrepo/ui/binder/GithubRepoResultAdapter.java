package com.learning.githubrepo.ui.binder;

import com.ahamed.multiviewadapter.DataListManager;
import com.ahamed.multiviewadapter.RecyclerAdapter;
import com.learning.githubrepo.model.GithubRepoData;

import java.util.ArrayList;


public class GithubRepoResultAdapter extends RecyclerAdapter {

    private DataListManager<GithubRepoData> dataListManager;

    public GithubRepoResultAdapter(ItemSelectedListener listener) {

        dataListManager = new DataListManager<>(this);

        addDataManager(dataListManager);

        registerBinder(new GithubRepoResultBinder(listener));
    }

    public void setData(ArrayList<GithubRepoData> reportsList) {
        dataListManager.set(reportsList);
    }

    public interface ItemSelectedListener {
        void onItemSelected(GithubRepoData response);
    }
}

