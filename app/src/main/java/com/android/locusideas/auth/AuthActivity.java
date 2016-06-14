package com.android.locusideas.auth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.android.locusideas.core.utils.ActivityUtils;
import com.facebook.appevents.AppEventsLogger;
import com.locusideas.locusideas.R;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;
import static com.locusideas.locusideas.BuildConfig.TWITTER_KEY;
import static com.locusideas.locusideas.BuildConfig.TWITTER_SECRET;

public class AuthActivity extends AppCompatActivity {

    private AuthFragment mAuthFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        mAuthFragment = (AuthFragment)getSupportFragmentManager().findFragmentById(R.id.auth_content);

        if (mAuthFragment == null){
            mAuthFragment = new AuthFragment();
        }

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                mAuthFragment, R.id.auth_content);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this.getApplication());

    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this.getApplication());
    }

}
