package com.android.locusideas.home.settings.di;

import com.android.locusideas.core.utils.injection.PerActivity;
import com.android.locusideas.home.settings.SettingsPresenter;
import com.android.locusideas.home.settings.SettingsView;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 06/07/16.
 */
@Module
public class SettingsModule {

    SettingsView settingsView;

    public SettingsModule(SettingsView settingsView){
        this.settingsView = settingsView;
    }

    @Provides
    @PerActivity
    public SettingsPresenter providesPresenter(){
        return new SettingsPresenter();
    }

}
