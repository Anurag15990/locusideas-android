package com.android.locusideas.auth.injection;

import com.android.locusideas.auth.AuthFragment;
import com.android.locusideas.core.utils.injection.PerActivity;

import dagger.Component;

/**
 * Created by satyaiyengar on 14/06/16.
 */

@Component(modules = {AuthModule.class})
@PerActivity
public interface AuthComponent {
    void inject(AuthFragment authFragment);
}
