package com.learning.githubrepo.ui.favorite;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahamed.multiviewadapter.DataListManager;
import com.evernote.android.state.State;
import com.learning.githubrepo.GithubRepoApp;
import com.learning.githubrepo.R;
import com.learning.githubrepo.model.db.FavoriteRepo;
import com.learning.githubrepo.ui.GithubRepoBaseFragment;
import com.learning.githubrepo.ui.favorite.binder.FavoriteRepoAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class FavoriteFragment extends GithubRepoBaseFragment implements FavoriteView, FavoriteRepoAdapter.ItemSelectedListener {

    @BindView(R.id.rcvFavList)
    RecyclerView rcvFavList;

    MenuItem searchItem;

    @Inject
    FavoritePresenter presenter;

    @State
    ArrayList<FavoriteRepo> favoriteRepos;

    FavoriteRepoAdapter adapter;
    DataListManager<FavoriteRepo> dataListManager;


    public FavoriteFragment() {
    }

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fav_repos, container, false);
        return v;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.search, menu);
        searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 0 && favoriteRepos != null) {
                    ArrayList<FavoriteRepo> favoriteReposList = new ArrayList<>();

                    for (FavoriteRepo favoriteRepo : favoriteRepos) {
                        if (favoriteRepo.name().contains(newText)) {
                            favoriteReposList.add(favoriteRepo);
                        }

                    }
                    adapter.setData(favoriteReposList);
                    adapter.notifyDataSetChanged();
                } else {
                    adapter.setData(favoriteRepos);
                }

                adapter.notifyDataSetChanged();
                return false;
            }
        });
        searchView.setQueryHint(getString(R.string.search_language));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initViews() {
        activityCallback.setToolbarAttribs("Favorites", true);
    }

    @Override
    protected void initDependencies() {
        GithubRepoApp.getGithubRepoComponent().inject(this);
    }

    @Override
    protected void onReady() {
        presenter.attachView(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvFavList.setLayoutManager(layoutManager);
        adapter = new FavoriteRepoAdapter(this);
        dataListManager = new DataListManager<>(adapter);
        adapter.addDataManager(dataListManager);
        presenter.getCachedRepos();

    }

    @Override
    protected void destroyPresenter() {
        presenter.detachView();
    }

    @Override
    public void onItemSelected(FavoriteRepo response) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setDataAndType(Uri.parse(response.url()), "text/html");
        startActivity(intent);
    }

    @Override
    public void onFavClicked(FavoriteRepo item) {
        FavoriteRepo favoriteRepo = FavoriteRepo.create(item.id(), item.name() != null ? item.name() : "",
                item.owner() != null ? item.owner() : "", item.url() != null ? item.url() : "",
                item.description() != null ? item.description() : "", item.starCount(), item.language() != null ? item.language() : "", item.status());

        presenter.deleteFromCache(favoriteRepo);

        presenter.getCachedRepos();
        adapter.notifyDataSetChanged();


        Toast.makeText(getContext(), "Succesfully unfavorited the repository", Toast.LENGTH_LONG).show();


    }

    @Override
    public void showCachedRepos(List<FavoriteRepo> data) {
        rcvFavList.setVisibility(View.VISIBLE);
        this.favoriteRepos = (ArrayList<FavoriteRepo>) data;
        adapter.setData(favoriteRepos);
        rcvFavList.setAdapter(adapter);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showContent() {

    }

    @Override
    public void showEmpty(String error) {
        rcvFavList.setVisibility(View.GONE);
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showToast(String message) {

    }


}
