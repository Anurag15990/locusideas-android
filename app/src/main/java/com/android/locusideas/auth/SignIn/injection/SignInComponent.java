package com.android.locusideas.auth.SignIn.injection;

import com.android.locusideas.auth.SignIn.SignInFragment;
import com.android.locusideas.core.data.auth.injection.AuthComponent;
import com.android.locusideas.core.utils.injection.PerActivity;
import dagger.Component;

/**
 * Created on 28/05/16.
 */
@PerActivity
@Component(dependencies = {AuthComponent.class} ,modules = {SignInModule.class})
public interface SignInComponent {
    void inject(SignInFragment signInFragment);
}
