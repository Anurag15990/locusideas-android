package com.android.locusideas;

import android.app.Application;

import com.android.locusideas.core.utils.SharedPreferencesManager;

import javax.inject.Inject;

/**
 * Created on 26/05/16.
 */
//TODO rename
public class LocusApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Inject
    SharedPreferencesManager sharedPreferencesManager;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerApplicationComponent.builder()
                                .applicationModule(new ApplicationModule(this))
                                .build()
                                .inject(this);
    }

    public SharedPreferencesManager getSharedPreferencesManager(){
        return sharedPreferencesManager;
    }

}
