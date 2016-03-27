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

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTypeFaceForActivity();
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

        Button twitterButton = (Button) findViewById(R.id.signUpTwitterButton);
        twitterButton.setTypeface(montserratBold);
    }


}
