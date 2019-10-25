package com.learning.githubrepo.core;

public interface BaseView {

    void showProgress();

    void showError(String error);

    void showContent();

    void showEmpty(String error);

    void showToast(String message);
}
