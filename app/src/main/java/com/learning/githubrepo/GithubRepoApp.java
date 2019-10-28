package com.learning.githubrepo;

import android.app.Application;

import com.learning.githubrepo.di.component.DaggerGithubRepoComponent;
import com.learning.githubrepo.di.module.GithubRepoModule;
import com.learning.githubrepo.di.component.GithubRepoComponent;

public class GithubRepoApp extends Application {

    /* Dagger instance is created and avoid all the boiler plate code .
       The basisTaskComponent instance is called from the activity.
       Dagger will generate all the code with the annotation proccessors and take care
       of dependency injection.*/

    private static GithubRepoComponent githubRepoComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        githubRepoComponent = DaggerGithubRepoComponent.builder().githubRepoModule(new GithubRepoModule(this)).build();
    }

    public static GithubRepoComponent getGithubRepoComponent() {
        return githubRepoComponent;
    }
}
