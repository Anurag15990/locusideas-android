package com.android.locusideas.auth.SignUp.injection;

import com.android.locusideas.auth.SignUp.SignUpFragment;
import com.android.locusideas.core.data.auth.injection.AuthComponent;
import com.android.locusideas.core.utils.injection.PerActivity;
import dagger.Component;

/**
 * Created by satyaiyengar on 14/06/16.
 */
@PerActivity
@Component(dependencies = {AuthComponent.class} ,modules = {SignUpModule.class})
public interface SignUpComponent {
    void inject(SignUpFragment signUpFragment);
}
