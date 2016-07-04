package com.android.locusideas.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import com.android.locusideas.LocusApplication;
import com.android.locusideas.SplashScreen.di.DaggerSplashScreenComponent;
import com.android.locusideas.SplashScreen.di.SplashScreenComponent;
import com.android.locusideas.SplashScreen.di.SplashScreenModule;
import com.android.locusideas.auth.AuthActivity;
import com.android.locusideas.core.ui.BaseActivity;
import com.android.locusideas.home.MainShellActivity;
import com.locusideas.locusideas.R;

/**
 * Created on 30/06/16.
 */
public class SplashScreenActivity extends BaseActivity<SplashScreenView, SplashScreenPresenter> implements SplashScreenView {

    SplashScreenComponent splashScreenComponent;

    Handler handler;

    boolean isPaused;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splashScreenComponent = DaggerSplashScreenComponent.builder()
                .applicationComponent(((LocusApplication) getApplicationContext()).getApplicationComponent())
                .splashScreenModule(new SplashScreenModule())
                .build();

        if(presenter == null){
            presenter = splashScreenComponent.getPresenter();
        }

        handler = new Handler();
    }

    @Override
    protected void onResume() {
        isPaused = false;
        super.onResume();
    }

    @Override
    protected void onPause() {
        isPaused = true;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        splashScreenComponent = null;
        handler = null;
    }

    @Override
    public void navigateToMainActivity(){
        handler.postDelayed(new NavigatationRunnable(this, new Intent(this, MainShellActivity.class)), 2000);
    }

    @Override
    public void navigateToAuthActivity(){
        handler.postDelayed(new NavigatationRunnable(this, new Intent(this, AuthActivity.class)), 2000);
    }

    @Override
    protected SplashScreenView getView() {
        return this;
    }

    private static class NavigatationRunnable implements Runnable{

        Intent nextActivity;
        SplashScreenActivity currentActivity;

        public NavigatationRunnable(SplashScreenActivity currentActivity, Intent nextActivity){
            this.nextActivity = nextActivity;
            this.currentActivity = currentActivity;
        }

        @Override
        public void run() {
            //TODO better way to maintain activity state
            if(!currentActivity.isPaused){
                currentActivity.startActivity(nextActivity);
                currentActivity.finish();
            }
            currentActivity = null;
        }

    }

}
