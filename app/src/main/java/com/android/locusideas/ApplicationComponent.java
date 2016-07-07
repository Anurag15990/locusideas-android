package com.android.locusideas;

import com.android.locusideas.core.utils.SharedPreferencesManager;
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

    void inject(LocusApplication la);
}
