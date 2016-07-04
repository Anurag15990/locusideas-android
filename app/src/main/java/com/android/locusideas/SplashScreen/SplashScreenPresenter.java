package com.android.locusideas.SplashScreen;

import com.android.locusideas.core.utils.SharedPreferencesManager;
import com.android.locusideas.core.utils.mvp.BasePresenter;

/**
 * Created on 30/06/16.
 */
public class SplashScreenPresenter extends BasePresenter<SplashScreenContract.View> {

    public final SharedPreferencesManager sharedPreferenceManager;

    public SplashScreenPresenter(SharedPreferencesManager sharedPreferencesManager){
        this.sharedPreferenceManager = sharedPreferencesManager;
    }

    @Override
    public void onResume() {
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
