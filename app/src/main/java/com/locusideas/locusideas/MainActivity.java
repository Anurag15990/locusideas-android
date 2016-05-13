package com.locusideas.locusideas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.facebook.FacebookSdk;

import com.facebook.appevents.AppEventsLogger;
import com.locusideas.locusideas.adapter.CustomPagerAdapter;
import com.locusideas.locusideas.auth.SignInActivity;
import com.locusideas.locusideas.auth.SignUpActivity;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "NHvVcFgKeWsCiev1YeCQHLQsd";
    private static final String TWITTER_SECRET = "RESkM8po4CKbkPGbncvOQcLODdOKXkbRyaTFtdThjYNFhKL38C";

    SharedPreferences sharedPreferences = getSharedPreferences()

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

        setTypeFaceForActivity();
    }


    public void setTypeFaceForActivity() {
        Typeface buttonFont = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-ExtraBold.otf");

        Button signUpButton = (Button) findViewById(R.id.signUpButton);
        signUpButton.setTypeface(buttonFont);

        Button signInButton = (Button) findViewById(R.id.signInButton);
        signInButton.setTypeface(buttonFont);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
