package com.android.locusideas.auth.signIn.injection;

import com.android.locusideas.auth.signIn.SignInContract;
import com.android.locusideas.auth.signIn.SignInPresenter;
import com.android.locusideas.core.data.auth.AuthDataContract;
import com.android.locusideas.core.utils.injection.PerActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created on 28/05/16.
 */
@Module
public class SignInModule{
    SignInContract.View mView;

    public SignInModule(SignInContract.View view){
        mView = view;
    }

    @Provides
    @PerActivity
    public SignInContract.Presenter getSignInPresenter(AuthDataContract.Services authServices){
        return new SignInPresenter(mView, authServices);
    }
}
