package com.android.locusideas;

import android.app.Application;
import android.content.Context;
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

        mApplicationComponent = DaggerApplicationComponent.builder()
                                .applicationModule(new ApplicationModule(this))
                                .build();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent(){
        return mApplicationComponent;
    }

    public static LocusApplication get(Context context){
        return (LocusApplication)context.getApplicationContext();
    }

    public SharedPreferencesManager getSharedPreferencesManager(){
        return sharedPreferencesManager;
    }

}
