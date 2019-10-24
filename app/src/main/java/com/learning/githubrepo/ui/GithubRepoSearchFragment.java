package com.learning.githubrepo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.evernote.android.state.State;
import com.learning.githubrepo.R;
import com.learning.githubrepo.core.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class GithubRepoSearchFragment extends BaseFragment {

    @BindView(R.id.et_search_query)
    EditText etSearchQuery;

    @State
    String searchQuery;


    public GithubRepoSearchFragment() {
    }

    public static GithubRepoSearchFragment newInstance() {
        return new GithubRepoSearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search_githubrepo, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDependencies() {

    }

    @Override
    protected void onReady() {

    }

    @Override
    protected void destroyPresenter() {

    }

    @OnClick(R.id.im_search_repo)
    public void onSearchRepoClicked() {
        if (isValid()) {
            searchQuery = etSearchQuery.getText().toString();
        }
    }

    public boolean isValid() {
        if (etSearchQuery.getText().toString().length() < 1) {
            etSearchQuery.setError(getString(R.string.enter_repo_name));
            return false;
        }
        return true;
    }
}
