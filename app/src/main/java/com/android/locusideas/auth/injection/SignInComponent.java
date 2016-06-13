package com.android.locusideas.auth.injection;

import com.android.locusideas.auth.SignInFragment;
import com.android.locusideas.auth.SignInPresenter;
import com.android.locusideas.core.data.auth.injection.AuthComponent;
import com.android.locusideas.core.utils.injection.PerActivity;
import com.android.locusideas.core.utils.injection.PerComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created on 28/05/16.
 */
@PerActivity
@Component(dependencies = {AuthComponent.class} ,modules = {SignInModule.class})
public interface SignInComponent {
    void inject(SignInFragment signInFragment);
    void inject(SignInPresenter signInPresenter);
}
