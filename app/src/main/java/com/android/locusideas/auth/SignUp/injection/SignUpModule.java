package com.android.locusideas.auth.signUp.injection;

import com.android.locusideas.auth.signUp.SignUpContract;
import com.android.locusideas.auth.signUp.SignUpPresenter;
import com.android.locusideas.core.data.auth.AuthDataContract;
import com.android.locusideas.core.utils.injection.PerActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by satyaiyengar on 14/06/16.
 */
@Module
public class SignUpModule{

    SignUpContract.View mView;

    public SignUpModule(SignUpContract.View view) {
        mView = view;
    }

    @Provides
    @PerActivity
    public SignUpContract.Presenter providesPresenter(AuthDataContract.Services authServices){
        return new SignUpPresenter(mView, authServices);
    }

}
