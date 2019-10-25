package com.learning.githubrepo.core;

public abstract class ErrorAction extends BaseErrorAction {

    @Override
    public void accept(Throwable throwable) throws Exception {
        super.accept(throwable);
        handleError(throwable);
    }

    protected abstract void handleError(Throwable throwable);
}
