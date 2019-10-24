package com.learning.githubrepo.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.learning.githubrepo.BuildConfig;
import com.learning.githubrepo.GithubRepoService;
import com.learning.githubrepo.api.GithubRepoApi;
import com.learning.githubrepo.server.RemoteServer;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class GithubRepoModule {

    private final Context context;

    public GithubRepoModule(Context context) {
        this.context = context;
    }

    /*Daggger has annotation processers . This method will return the retrofit instance.
     RxJavaCallAdapterFactory will return an instance which creates observables  */
    @Provides
    GithubRepoService provideService(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        return retrofit.create(GithubRepoService.class);
    }

    //HTTP Logging interceptor is for logging the request and response.
    // This method is returning an instance of okhttpclient
    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        return new OkHttpClient.Builder().writeTimeout(60000, TimeUnit.MILLISECONDS)
                .connectTimeout(60000, TimeUnit.MILLISECONDS)
                .readTimeout(60000, TimeUnit.MILLISECONDS)
                .addInterceptor(logging)
                .build();
    }

    @Provides
    RemoteServer providesRemoteServer(GithubRepoService githubRepoService) {
        return new RemoteServer(githubRepoService);
    }

    @Provides
    GithubRepoApi provideGithubRepoApi(RemoteServer remoteServer) {
        return new GithubRepoApi(remoteServer);
    }


}
