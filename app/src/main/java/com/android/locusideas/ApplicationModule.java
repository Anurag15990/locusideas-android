package com.android.locusideas;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.android.locusideas.core.utils.SharedPreferencesManager;
import com.android.locusideas.core.utils.mvp.PresenterManager;
import com.android.locusideas.home.projects.project.ProjectHolder;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created on 26/05/16.
 */
@Module
public class ApplicationModule {

    private LocusApplication mApplication;

    public ApplicationModule(LocusApplication app) {
        mApplication = app;
    }

    @Provides
    @Singleton
    public LocusApplication getApplication(){
        return mApplication;
    }

    @Provides
    @Singleton
    public SharedPreferences getSharedPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(mApplication);
    }

    @Provides
    @Singleton
    public SharedPreferencesManager getSharedPreferenceManager(SharedPreferences sp){
        return new SharedPreferencesManager(sp);
    }

    @Provides
    @Singleton
    public PresenterManager providesPresenterManager(){
        return new PresenterManager();
    }

    @Provides
    @Singleton
    public ProjectHolder providesProjectHolder(){
        return new ProjectHolder();
    }

}
