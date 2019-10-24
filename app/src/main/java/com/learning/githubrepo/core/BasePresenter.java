package com.learning.githubrepo.core;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<V extends BaseView> {

    protected V view;
    private CompositeDisposable disposible;

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
        if (null != disposible && disposible.isDisposed()) {
            disposible.clear();
        }
    }

    protected final void addToSubscription(Disposable disposable) {
        if (null == disposible) {
            disposible = new CompositeDisposable();
        }
        disposible.add(disposable);
    }

    protected boolean isViewAttached() {
        return this.view != null;
    }

    protected void showProgress() {
        if (isViewAttached()) {
            view.showProgress();
        }
    }

    protected void showError(String error) {
        if (isViewAttached()) {
            view.showError(error);
        }
    }

    protected boolean showContent() {
        if (isViewAttached()) {
            view.showContent();
            return true;
        }
        return false;
    }
}
