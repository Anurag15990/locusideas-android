package com.android.locusideas.SplashScreen;

import android.os.Handler;
import com.android.locusideas.core.utils.SharedPreferencesManager;

/**
 * Created on 30/06/16.
 */
public class SplashScreenPresenter implements SplashScreenContract.Presenter {

    public final SharedPreferencesManager sharedPreferenceManager;
    SplashScreenContract.View view;
    Handler handler;

    public SplashScreenPresenter(SplashScreenContract.View view,
                                 SharedPreferencesManager sharedPreferencesManager,
                                 Handler handler){
        this.view = view;
        this.sharedPreferenceManager = sharedPreferencesManager;
        this.handler = handler;
    }

    @Override
    public void start() {
        navigateBasedOnLoginStatus();
    }

    private void navigateBasedOnLoginStatus(){
        handler.postDelayed(new NavigateBasedOnLoginStatus(this), 2000);
    }

    private static class NavigateBasedOnLoginStatus implements Runnable{
        SplashScreenPresenter presenter;

        public NavigateBasedOnLoginStatus(SplashScreenPresenter presenter){
            this.presenter = presenter;
        }

        @Override
        public void run() {
            if (presenter.view.isActive()) {
                if (presenter.sharedPreferenceManager.getUserAuthToken() != null) {
                    presenter.view.navigateToMainActivity();
                } else {
                    presenter.view.navigateToAuthActivity();
                }
            }
        }

    }


}
