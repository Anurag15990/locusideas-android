package com.android.locusideas.auth.signIn;

import android.util.Log;
import com.android.locusideas.core.data.auth.AuthDataContract;
import com.facebook.AccessToken;

/**
 * Created on 26/05/16.
 */
public class SignInPresenter implements SignInContract.Presenter{

    private SignInContract.View mSignInView;

    private AuthDataContract.Services mAuthDataManager;

    public SignInPresenter(SignInContract.View signInView, AuthDataContract.Services authDataManager){
        mSignInView  = signInView;
        mAuthDataManager = authDataManager;
        mAuthDataManager.setSignInPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void onForgotPassword() {

    }

    @Override
    public void onSignIn(String emailId, String password) {
        mAuthDataManager.onSignInWithEmailId(emailId, password);
    }

    @Override
    public void onSignInViaFacebook(AccessToken accessToken, String fbId){
        mAuthDataManager.onSignInViaFacebook(accessToken, fbId);
    }

    @Override
    public void onSignInSuccess() {
        Log.d("Presenter", "Signin successful");
    }

    @Override
    public void onSignInFailure(String errorMessage) {
        Log.e("Presenter", errorMessage);
    }
}
