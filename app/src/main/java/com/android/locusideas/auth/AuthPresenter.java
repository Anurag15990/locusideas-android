package com.android.locusideas.auth;

/**
 * Created by satyaiyengar on 14/06/16.
 */

public class AuthPresenter implements AuthContract.Presenter {

    private AuthContract.View mView;

    public AuthPresenter(AuthContract.View view){
        mView = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void onClickSignIn() {
        mView.openSignInActivity();
    }

    @Override
    public void onCLickSignUp() {
        mView.openSignUpActivity();
    }

}
