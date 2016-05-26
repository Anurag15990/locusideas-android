package com.android.locusideas;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created on 26/05/16.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(LocusApplication la);
}
