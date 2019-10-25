package com.learning.githubrepo.ui.searchrepo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahamed.multiviewadapter.DataListManager;
import com.evernote.android.state.State;
import com.learning.githubrepo.GithubRepoApp;
import com.learning.githubrepo.R;
import com.learning.githubrepo.model.GithubRepoData;
import com.learning.githubrepo.model.db.FavoriteRepo;
import com.learning.githubrepo.ui.GithubRepoBaseFragment;
import com.learning.githubrepo.ui.searchrepo.binder.GithubRepoResultAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class GithubRepoSearchFragment extends GithubRepoBaseFragment implements GithubRepoView, GithubRepoResultAdapter.ItemSelectedListener {

    @BindView(R.id.et_search_query)
    EditText etSearchQuery;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.rcv_repo_list)
    RecyclerView rcvRepoList;

    //    @State
    String searchQuery;
    @Inject
    GithubRepoPresenter presenter;

    @State
    ArrayList<GithubRepoData> githubRepoData = new ArrayList<>();

    GithubRepoResultAdapter adapter;


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
        presenter.attachView(this);
        activityCallback.setToolbarAttribs("Search repository", false);

    }

    @Override
    protected void initDependencies() {
        GithubRepoApp.getGithubRepoComponent().inject(this);

    }

    @Override
    protected void onReady() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvRepoList.setLayoutManager(layoutManager);
        adapter = new GithubRepoResultAdapter(this);
        adapter.setData(githubRepoData);
        rcvRepoList.setAdapter(adapter);

    }

    @Override
    protected void destroyPresenter() {

    }

    @OnClick(R.id.im_search_repo)
    public void onSearchRepoClicked() {
        if (isValid()) {
            searchQuery = etSearchQuery.getText().toString();
            presenter.getRepoList(searchQuery);
        }
    }

    public boolean isValid() {
        if (etSearchQuery.getText().toString().length() < 1) {
            etSearchQuery.setError(getString(R.string.enter_repo_name));
            return false;
        }
        return true;
    }

    @Override
    public void showRepos(List<GithubRepoData> data) {
        this.githubRepoData = (ArrayList<GithubRepoData>) data;
        if (githubRepoData != null) {
            adapter.setData(githubRepoData);
        }

    }

    @OnClick(R.id.fab_fav)
    void favButtonClicked() {
        activityCallback.callFavoriteFragment();
    }


    @Override
    public void onItemSelected(GithubRepoData response) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setDataAndType(Uri.parse(response.url()), "text/html");
        startActivity(intent);
    }

    @Override
    public void onFavClicked(GithubRepoData item) {
        FavoriteRepo favoriteRepo = FavoriteRepo.create(item.id(), item.name() != null ? item.name() : "",
                item.owner() != null ? item.owner() : "", item.url() != null ? item.url() : "",
                item.description() != null ? item.description() : "", item.starCount(), item.language() != null ? item.language() : "");
        presenter.insertToDb(favoriteRepo);

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showContent() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showEmpty(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }


}
