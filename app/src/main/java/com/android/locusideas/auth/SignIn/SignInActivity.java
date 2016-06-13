package com.android.locusideas.auth.SignIn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.locusideas.core.utils.ActivityUtils;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.locusideas.locusideas.R;

public class SignInActivity extends AppCompatActivity {

    private LoginButton facebookLoginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        SignInFragment signInFragment = (SignInFragment)getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (signInFragment == null){
            signInFragment = new SignInFragment();
        }

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                signInFragment, R.id.contentFrame);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
