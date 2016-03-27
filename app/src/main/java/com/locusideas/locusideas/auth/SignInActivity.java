package com.locusideas.locusideas.auth;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.locusideas.locusideas.R;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        setTypeFaceForActivity();
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

        Button twitterButton = (Button) findViewById(R.id.signInTwitterButton);
        twitterButton.setTypeface(montserratBold);
    }

    //TODO: Change Facebook & Twitter Buttons
}
