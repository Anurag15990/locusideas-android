package com.android.locusideas;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 26/05/16.
 */
@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application app) {
        mApplication = app;
    }

    @Singleton @Provides
    public SharedPreferences getSharedPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(mApplication);
    }

}
