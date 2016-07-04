package com.android.locusideas.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.android.locusideas.LocusApplication;
import com.android.locusideas.SplashScreen.di.DaggerSplashScreenComponent;
import com.android.locusideas.SplashScreen.di.SplashScreenComponent;
import com.android.locusideas.SplashScreen.di.SplashScreenModule;
import com.android.locusideas.auth.AuthActivity;
import com.android.locusideas.core.utils.mvp.PresenterManager;
import com.android.locusideas.home.MainShellActivity;
import com.locusideas.locusideas.R;

/**
 * Created on 30/06/16.
 */
public class SplashScreenActivity extends AppCompatActivity implements SplashScreenView {

    SplashScreenPresenter presenter;

    PresenterManager presenterManager;

    SplashScreenComponent splashScreenComponent;

    Handler handler;

    boolean isPaused;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //TODO move to BaseActivity
        presenterManager = ((LocusApplication)getApplicationContext()).getPresenterManager();

        splashScreenComponent = DaggerSplashScreenComponent.builder()
                .applicationComponent(((LocusApplication) getApplicationContext()).getApplicationComponent())
                .splashScreenModule(new SplashScreenModule())
                .build();

        if(savedInstanceState != null) {
            presenter = presenterManager.restorePresenter(savedInstanceState);
        } else {
            presenter = splashScreenComponent.getPresenter();
        }

        handler = new Handler();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.bindView(this);
        isPaused = false;
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unBindView();
        isPaused = true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenterManager.savePresenter(presenter, outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        splashScreenComponent = null;
        handler = null;
        presenterManager = null;
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
