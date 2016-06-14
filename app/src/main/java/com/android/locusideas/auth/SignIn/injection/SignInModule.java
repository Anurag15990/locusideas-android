package com.android.locusideas.auth.SignIn.injection;

import com.android.locusideas.auth.SignIn.SignInContract;
import com.android.locusideas.auth.SignIn.SignInPresenter;
import com.android.locusideas.core.BaseModule;
import com.android.locusideas.core.data.auth.AuthDataContract;
import com.android.locusideas.core.ui.BaseView;
import com.android.locusideas.core.utils.injection.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 28/05/16.
 */
@Module
public class SignInModule<T extends SignInContract.View> extends BaseModule<T> {

    public SignInModule(T view){
        super(view);
    }

    @Provides
    @PerActivity
    public SignInContract.Presenter getSignInPresenter(AuthDataContract.Services authServices){
        return new SignInPresenter(getView(), authServices);
    }
}
