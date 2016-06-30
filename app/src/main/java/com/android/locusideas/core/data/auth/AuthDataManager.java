package com.android.locusideas.core.data.auth;

import android.util.Log;
import com.android.locusideas.auth.SignIn.SignInPresenter;
import com.android.locusideas.auth.SignUp.SignUpPresenter;
import com.android.locusideas.core.data.auth.local.AuthLocalDataService;
import com.android.locusideas.core.data.auth.remote.AuthRemoteDataService;
import com.android.locusideas.core.data.models.ApiError;
import com.android.locusideas.core.utils.SharedPreferencesManager;
import com.facebook.AccessToken;

/**
 * Created on 28/05/16.
 */
public class AuthDataManager implements AuthDataContract.Services, AuthDataContract.Callbacks{

    //TODO create local data service to store locally
    AuthRemoteDataService mAuthRemoteService;
    AuthLocalDataService mAuthLocalService;

    SignInPresenter mSignInPresenter;
    SignUpPresenter mSignUpPresenter;

    public AuthDataManager(AuthRemoteDataService authRemoteService, AuthLocalDataService authLocalDataService, SharedPreferencesManager sharedPreferencesManager){
        mAuthRemoteService = authRemoteService;
        mAuthLocalService = authLocalDataService;
        mAuthRemoteService.setAuthDataCallbacks(this, sharedPreferencesManager);
    }

    @Override
    public void setSignInPresenter(SignInPresenter signInPresenter){
        mSignInPresenter = signInPresenter;
    }

    @Override
    public void setSignUpPresenter(SignUpPresenter signUpPresenter){
        mSignUpPresenter = signUpPresenter;
    }

    @Override
    public void onSignInViaFacebook(AccessToken accessToken, String fbId) {
        mAuthRemoteService.signInViaFacebook(accessToken, fbId);
    }

    @Override
    public void onSignUpWithEmailId(String emailId, String password) {
        mAuthRemoteService.signUpWithEmailId(emailId, password);
    }

    @Override
    public void onSignUpSuccess(String token) {
        Log.d("Presenter", "Authorization successful");
        mAuthLocalService.saveUserToken(token);
        mSignUpPresenter.onSignUpSuccess();
    }

    @Override
    public void onSignUpFailure(ApiError error) {
        mSignUpPresenter.onSignUpFailure(error.getMessage());
    }

    @Override
    public void onSignInWithEmailId(String emailId, String password) {
        mAuthRemoteService.signInWithEmailId(emailId, password);
    }

    @Override
    public void onSignInSuccess(String token) {
        Log.d("Presenter", "Authorization successful");
        mAuthLocalService.saveUserToken(token);
        mSignInPresenter.onSignInSuccess();
    }

    @Override
    public void onSignInFailure(ApiError error) {
        mSignInPresenter.onSignInFailure(error.getMessage());
    }

}
