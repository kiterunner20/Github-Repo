package com.learning.githubrepo.core;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.evernote.android.state.StateSaver;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (null != getArguments()) {
            initArguments(getArguments());
        }
        StateSaver.restoreInstanceState(this, savedInstanceState);
        initDependencies();
        initViews();
        onReady();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        StateSaver.saveInstanceState(this, outState);
    }

    @Override
    public void onDestroyView() {
        destroyPresenter();
        super.onDestroyView();
    }

    protected abstract void initArguments(Bundle arguments);

    protected abstract void initViews();

    protected abstract void initDependencies();

    protected abstract void onReady();

    protected abstract void destroyPresenter();
}
