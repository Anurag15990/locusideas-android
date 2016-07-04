package com.android.locusideas.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.android.locusideas.LocusApplication;
import com.android.locusideas.SplashScreen.injection.DaggerSplashScreenComponent;
import com.android.locusideas.SplashScreen.injection.SplashScreenComponent;
import com.android.locusideas.SplashScreen.injection.SplashScreenModule;
import com.android.locusideas.auth.AuthActivity;
import com.android.locusideas.home.MainShellActivity;
import com.locusideas.locusideas.R;
import javax.inject.Inject;

/**
 * Created on 30/06/16.
 */
public class SplashScreenActivity extends AppCompatActivity implements SplashScreenContract.View{

    @Inject
    SplashScreenContract.Presenter presenter;

    SplashScreenComponent splashScreenComponent;

    Handler handler;

    boolean isPaused;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        inject();
        handler = new Handler();
    }

    public void inject(){
        splashScreenComponent = DaggerSplashScreenComponent.builder().applicationComponent(((LocusApplication)getApplicationContext()).getApplicationComponent())
                .splashScreenModule(new SplashScreenModule(this))
                .build();

        splashScreenComponent.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
        isPaused = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isPaused = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        splashScreenComponent = null;
        handler = null;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void navigateToMainActivity(){
        handler.postDelayed(new NavigatationRunnable(this, new Intent(this, MainShellActivity.class)), 2000);
    }

    @Override
    public void navigateToAuthActivity(){
        handler.postDelayed(new NavigatationRunnable(this, new Intent(this, AuthActivity.class)), 2000);
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
