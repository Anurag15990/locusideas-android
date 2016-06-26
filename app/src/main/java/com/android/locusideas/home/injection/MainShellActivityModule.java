package com.android.locusideas.home.injection;

import com.android.locusideas.core.utils.injection.PerActivity;
import com.android.locusideas.home.MainShellFragmentsProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 25/06/16.
 */

@Module
public class MainShellActivityModule{

    @Provides
    @PerActivity
    public MainShellFragmentsProvider provideMainShellFragmentsProvider(){
        return new MainShellFragmentsProvider();
    }

}
