package com.android.locusideas.SplashScreen.injection;

import com.android.locusideas.ApplicationComponent;
import com.android.locusideas.SplashScreen.SplashScreenActivity;
import com.android.locusideas.core.utils.injection.PerActivity;
import dagger.Component;

/**
 * Created on 30/06/16.
 */
@PerActivity
@Component(dependencies = {ApplicationComponent.class}, modules = {SplashScreenModule.class})
public interface SplashScreenComponent {
    void inject(SplashScreenActivity splashScreenActivity);
}
