package com.android.locusideas.auth.signIn;

import com.android.locusideas.core.ui.BasePresenter;
import com.android.locusideas.core.ui.BaseView;
import com.facebook.AccessToken;

/**
 * Created on 26/05/16.
 */
public interface SignInContract {

    interface Presenter extends BasePresenter{
        void onForgotPassword();
        void onSignIn(String userId, String password);
        void onSignInViaFacebook(AccessToken accessToken, String fbId);
        void onSignInSuccess();
        void onSignInFailure(String errorMessage);
    }

    interface View extends BaseView{
        boolean isActive();

        void showLoader();

        void hideLoader();

        void navigateToMainActivity();
    }
}
