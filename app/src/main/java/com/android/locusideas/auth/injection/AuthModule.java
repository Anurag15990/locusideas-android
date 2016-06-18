package com.android.locusideas.auth.injection;

import com.android.locusideas.auth.AuthContract;
import com.android.locusideas.auth.AuthPresenter;
import com.android.locusideas.core.utils.injection.PerActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by satyaiyengar on 14/06/16.
 */
@Module
public class AuthModule {

    private AuthContract.View mView;

    public AuthModule(AuthContract.View view){
        mView = view;
    }

    @Provides
    @PerActivity
    public AuthContract.Presenter providePresenter(){
        return new AuthPresenter(mView);
    }

}
