package com.android.locusideas.core.data.auth;

import com.android.locusideas.auth.SignIn.SignInPresenter;
import com.android.locusideas.auth.SignUpPresenter;
import com.android.locusideas.core.data.models.ApiError;

/**
 * Created on 28/05/16.
 */
public interface AuthDataContract {

    interface Callbacks{
        void onSignInSuccess(String token);
        void onSignInFailure(ApiError error);
        void onSignUpSuccess(String token);
        void onSignUpFailure(ApiError error);
    }

    interface Services{
        void onSignUpWithEmailId(String emailId, String password);
        void onSignInWithEmailId(String emailId, String password);
        void setSignInPresenter(SignInPresenter signInPresenter);
        void setSignUpPresenter(SignUpPresenter signUpPresenter);
    }

}
