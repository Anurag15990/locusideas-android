package com.android.locusideas.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.android.locusideas.adapter.CustomPagerAdapter;
import com.android.locusideas.auth.SignIn.SignInActivity;
import com.android.locusideas.auth.SignUp.SignUpActivity;
import com.facebook.appevents.AppEventsLogger;
import com.locusideas.locusideas.R;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;
import static com.locusideas.locusideas.BuildConfig.TWITTER_KEY;
import static com.locusideas.locusideas.BuildConfig.TWITTER_SECRET;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);
        int[] mResources = {
                R.drawable.splash_1,
                R.drawable.splash_2,
                R.drawable.splash_3,
                R.drawable.splash_4
        };

        try {
            CustomPagerAdapter mCustomPagerAdapter = new CustomPagerAdapter(this, mResources);
            mViewPager.setAdapter(mCustomPagerAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void openSignInActivity(View view) {
        Intent signInIntent = new Intent(this, SignInActivity.class);
        startActivity(signInIntent);
    }

    public void openSignUpActivity(View view) {
        Intent signUpIntent = new Intent(this, SignUpActivity.class);
        startActivity(signUpIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

}
