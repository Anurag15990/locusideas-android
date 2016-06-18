package com.android.locusideas.auth;

import com.android.locusideas.core.ui.BasePresenter;
import com.android.locusideas.core.ui.BaseView;

/**
 * Created by satyaiyengar on 14/06/16.
 */

public interface AuthContract {
    interface Presenter extends BasePresenter{
        void onClickSignIn();
        void onCLickSignUp();
    }

    interface View extends BaseView{

        void openSignInActivity();

        void openSignUpActivity();
    }
}
