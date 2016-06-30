package com.android.locusideas.SplashScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.android.locusideas.core.utils.ActivityUtils;
import com.locusideas.locusideas.R;

/**
 * Created on 30/06/16.
 */
public class SplashScreenActivity extends AppCompatActivity{

    private SplashScreenFragment splashFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        addFragment();
    }

    public void addFragment(){
        splashFragment = (SplashScreenFragment)getSupportFragmentManager().findFragmentById(R.id.splash_content);

        if (splashFragment == null){
            splashFragment = new SplashScreenFragment();
        }

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                splashFragment, R.id.splash_content);
    }

}
