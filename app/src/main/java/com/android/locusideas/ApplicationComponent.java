package com.android.locusideas;

import com.android.locusideas.core.utils.SharedPreferencesManager;
import com.android.locusideas.core.utils.mvp.PresenterManager;
import javax.inject.Singleton;
import dagger.Component;

/**
 * Created on 26/05/16.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    LocusApplication locusApplication();
    SharedPreferencesManager sharedPreferenceManager();
    PresenterManager providesPresenterManager();

    void inject(LocusApplication la);
}
