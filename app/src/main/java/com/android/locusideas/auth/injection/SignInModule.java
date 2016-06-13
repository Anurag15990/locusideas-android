package com.android.locusideas.auth.injection;

import com.android.locusideas.auth.SignInContract;
import com.android.locusideas.auth.SignInPresenter;
import com.android.locusideas.core.data.auth.AuthDataContract;
import com.android.locusideas.core.data.auth.injection.AuthModule;
import com.android.locusideas.core.utils.injection.PerActivity;
import com.android.locusideas.core.utils.injection.PerComponent;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 28/05/16.
 */
@Module
public class SignInModule {

    private SignInContract.View mView;

    public SignInModule(SignInContract.View view){
        mView = view;
    }

    @Provides
    @PerActivity
    public SignInContract.Presenter getSignInPresenter(AuthDataContract.Services authServices){
        return new SignInPresenter(mView, authServices);
    }
}
