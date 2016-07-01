package com.android.locusideas.SplashScreen;

import com.android.locusideas.core.utils.SharedPreferencesManager;

/**
 * Created on 30/06/16.
 */
public class SplashScreenPresenter implements SplashScreenContract.Presenter {

    public final SharedPreferencesManager sharedPreferenceManager;
    SplashScreenContract.View view;

    public SplashScreenPresenter(SplashScreenContract.View view,
                                 SharedPreferencesManager sharedPreferencesManager){
        this.view = view;
        this.sharedPreferenceManager = sharedPreferencesManager;
    }

    @Override
    public void start() {
        navigateBasedOnLoginStatus();
    }

    private void navigateBasedOnLoginStatus(){
        if (sharedPreferenceManager.getUserAuthToken() != null) {
            view.navigateToMainActivity();
        } else {
            view.navigateToAuthActivity();
        }
    }

}
