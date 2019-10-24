package com.learning.githubrepo.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.evernote.android.state.State;
import com.learning.githubrepo.R;
import com.learning.githubrepo.core.BaseActivity;

public class GithubRepoActivity extends BaseActivity {

    @State
    int currentScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initIntentExtras(Bundle extras) {

    }

    @Override
    protected void initDependencies() {

    }

    @Override
    protected void initViews() {
        currentScreen = 0;
        if (getSupportFragmentManager().findFragmentById(R.id.layout_activity_search_repo) == null) {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag("search_github_repo");

            if (null == fragment) {
                fragment = GithubRepoSearchFragment.newInstance();
            }
            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction transaction = fragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .replace(R.id.layout_activity_search_repo, fragment, "search_github_repo");
            if (currentScreen != 0) {
                transaction.addToBackStack(null);
            }
            transaction.commit();
        }
    }

    @Override
    protected void onReady() {

    }

    @Override
    protected void destroyPresenter() {

    }
}
