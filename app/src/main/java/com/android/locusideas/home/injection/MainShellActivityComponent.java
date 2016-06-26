package com.android.locusideas.home.injection;

import com.android.locusideas.core.utils.injection.PerActivity;
import com.android.locusideas.home.MainShellActivity;
import com.android.locusideas.home.MainShellFragmentsProvider;

import dagger.Component;

/**
 * Created on 25/06/16.
 */
@PerActivity
@Component(modules = {MainShellActivityModule.class})
public interface MainShellActivityComponent {
    MainShellFragmentsProvider provideMainShellFragmentsProvider();

    void inject(MainShellActivity mainShellActivity);
}
