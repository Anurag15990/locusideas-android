package com.locusideas.locusideas.auth;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.locusideas.locusideas.R;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class SignUpActivity extends AppCompatActivity {

    private TwitterLoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTypeFaceForActivity();
        handleTwitterLoginForSignIn();
    }

    public void setTypeFaceForActivity() {

        Typeface montserratLight = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Light.otf");

        EditText nameText = (EditText) findViewById(R.id.signUpNameText);
        EditText emailText = (EditText) findViewById(R.id.signUpEmailText);
        EditText passwordText = (EditText) findViewById(R.id.signUpPasswordText);
        EditText confirmText = (EditText) findViewById(R.id.signUpConfirmText);


        nameText.setTypeface(montserratLight);
        emailText.setTypeface(montserratLight);
        passwordText.setTypeface(montserratLight);
        confirmText.setTypeface(montserratLight);

        Typeface montserratExtraBold = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-ExtraBold.otf");

        Button signInButton = (Button) findViewById(R.id.signUpActivityButton);
        signInButton.setTypeface(montserratExtraBold);

        TextView orSignInWith = (TextView)findViewById(R.id.orSignUpWithText);
        orSignInWith.setTypeface(montserratLight);

        Typeface montserratBold = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Bold.otf");

        Button facebookButton = (Button) findViewById(R.id.signUpFacebookButton);
        facebookButton.setTypeface(montserratBold);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

    public void handleTwitterLoginForSignIn() {
        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button_for_signup);
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
                System.out.println(result.data);
                // Do something with the result, which provides the email address
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
            }
        });
    }

}
