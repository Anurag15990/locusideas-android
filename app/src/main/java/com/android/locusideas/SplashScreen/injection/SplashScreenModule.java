package com.android.locusideas.SplashScreen.injection;

import com.android.locusideas.SplashScreen.SplashScreenContract;
import com.android.locusideas.SplashScreen.SplashScreenPresenter;
import com.android.locusideas.core.utils.SharedPreferencesManager;
import com.android.locusideas.core.utils.injection.PerActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created on 30/06/16.
 */
@Module
public class SplashScreenModule {

    SplashScreenContract.View view;

    public SplashScreenModule(SplashScreenContract.View view){
        this.view = view;
    }

    @Provides
    @PerActivity
    public SplashScreenContract.Presenter providesPresenter(SharedPreferencesManager sharedPreferencesManager){
        return new SplashScreenPresenter(view, sharedPreferencesManager);
    }

}
