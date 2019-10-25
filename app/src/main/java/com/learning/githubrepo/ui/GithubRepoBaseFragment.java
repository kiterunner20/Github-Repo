package com.learning.githubrepo.ui;

import android.content.Context;

import com.learning.githubrepo.core.BaseFragment;

public abstract class GithubRepoBaseFragment extends BaseFragment {

    protected GithubRepoCallback activityCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activityCallback = (GithubRepoCallback) context;
    }

    public interface GithubRepoCallback {

    }
}
