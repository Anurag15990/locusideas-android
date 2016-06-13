package com.android.locusideas.auth.SignIn;

import com.android.locusideas.core.ui.BasePresenter;
import com.android.locusideas.core.ui.BaseView;

/**
 * Created on 26/05/16.
 */
public interface SignInContract {

    interface Presenter extends BasePresenter{
        void onForgotPassword();
        void onSignIn(String userId, String password);
        void onSignInSuccess();
        void onSignInFailure(String errorMessage);
    }

    interface View extends BaseView{
        boolean isActive();
    }
}
