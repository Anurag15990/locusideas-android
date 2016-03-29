package com.locusideas.locusideas.auth;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.content.Intent;

import com.twitter.sdk.android.core.identity.*;
import com.twitter.sdk.android.core.*;

import com.locusideas.locusideas.R;

public class SignInActivity extends AppCompatActivity {

    private TwitterLoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        setTypeFaceForActivity();
        handleTwitterLoginForSignIn();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

    public void setTypeFaceForActivity() {

        Typeface montserratLight = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Light.otf");

        EditText emailText = (EditText) findViewById(R.id.signinEmailText);
        EditText passwordText = (EditText) findViewById(R.id.signInPasswordText);

        emailText.setTypeface(montserratLight);
        passwordText.setTypeface(montserratLight);

        Typeface montserratExtraBold = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-ExtraBold.otf");

        Button signInButton = (Button) findViewById(R.id.signInActivityButton);
        signInButton.setTypeface(montserratExtraBold);

        Typeface montserratSemiBold = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-SemiBold.otf");

        TextView forgotPassword = (TextView)findViewById(R.id.forgotPassword);
        forgotPassword.setTypeface(montserratSemiBold);

        TextView orSignInWith = (TextView)findViewById(R.id.orSignInWithText);
        orSignInWith.setTypeface(montserratLight);

        Typeface montserratBold = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Bold.otf");

        Button facebookButton = (Button) findViewById(R.id.signInFacebookButton);
        facebookButton.setTypeface(montserratBold);
    }

    public void handleTwitterLoginForSignIn() {
        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                TwitterSession session = result.data;
                // TODO: Remove toast and use the TwitterSession's userID
                requestTwitterEmailId(session);
                // with your app's user model
                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });
    }

    public void requestTwitterEmailId(TwitterSession session) {
        TwitterAuthClient authClient = new TwitterAuthClient();
        authClient.requestEmail(session, new Callback<String>() {
            @Override
            public void success(Result<String> result) {
                // Do something with the result, which provides the email address
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
            }
        });
    }

}
