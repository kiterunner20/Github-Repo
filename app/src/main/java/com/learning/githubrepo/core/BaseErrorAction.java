package com.learning.githubrepo.core;

import android.util.Log;

import androidx.annotation.CallSuper;

import java.net.UnknownHostException;

import io.reactivex.functions.Consumer;

public class BaseErrorAction implements Consumer<Throwable> {

    @CallSuper
    @Override
    public void accept(Throwable throwable) throws Exception {
        if (!(throwable instanceof UnknownHostException)) {
            Log.e("Exception", throwable.toString());
        }
    }
}
