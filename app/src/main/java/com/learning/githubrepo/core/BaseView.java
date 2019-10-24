package com.learning.githubrepo.core;

public interface BaseView {

    void showProgress();

    void toggleAsyncProgress(boolean show);

    void showError(String error);

    void showContent();

    void showEmpty(String error);

    void showToast(String message);
}
