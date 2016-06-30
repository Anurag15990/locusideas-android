package com.android.locusideas.auth.SignUp;

import android.util.Log;

import com.android.locusideas.core.data.auth.AuthDataContract;

/**
 * Created on 28/05/16.
 */
public class SignUpPresenter implements SignUpContract.Presenter {

    private SignUpContract.View mView;
    private AuthDataContract.Services mAuthDataManager;

    public SignUpPresenter(SignUpContract.View view, AuthDataContract.Services authDataManager){
        mView = view;
        mAuthDataManager = authDataManager;
        mAuthDataManager.setSignUpPresenter(this);
    }

    @Override
    public void onClickSignUp(String email, String password) {
        mView.showLoader();
        mAuthDataManager.onSignUpWithEmailId(email, password);
    }

    @Override
    public void start() {

    }

    public void onSignUpSuccess(){
        mView.hideLoader();
        mView.navigateToMainActivity();
    }

    public void onSignUpFailure(String errorMessage){
        mView.hideLoader();
        Log.e("Presenter", errorMessage);
    }
}
