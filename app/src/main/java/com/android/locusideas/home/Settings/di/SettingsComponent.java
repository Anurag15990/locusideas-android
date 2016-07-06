package com.android.locusideas.home.settings.di;

import com.android.locusideas.core.utils.injection.PerActivity;
import com.android.locusideas.home.settings.SettingsPresenter;
import com.android.locusideas.home.settings.SettingsView;

import dagger.Component;

/**
 * Created on 06/07/16.
 */

@PerActivity
@Component(modules = {SettingsModule.class})
public interface SettingsComponent {
    SettingsPresenter getPresenter();
    void inject(SettingsView settingsView);
}
