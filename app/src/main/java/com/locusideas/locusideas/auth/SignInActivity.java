package com.locusideas.locusideas.auth;

import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import android.content.Intent;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.locusideas.locusideas.requests.User.TwitterAuthRequest;
import com.locusideas.locusideas.responses.TokenResponse;
import com.locusideas.locusideas.routers.BaseRouterService;
import com.locusideas.locusideas.services.UserService;
import com.locusideas.locusideas.services.UserServiceCallback;
import com.locusideas.locusideas.utilites.SharedPreferencesManager;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.identity.*;
import com.twitter.sdk.android.core.*;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import org.json.JSONObject;

import com.locusideas.locusideas.R;

import retrofit2.*;

public class SignInActivity extends AppCompatActivity {

    private LoginButton facebookLoginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        setTypeFaceForActivity();
        handleFacebookLogin();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Setting Fonts for the activity
     */
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
    }

    /**
     * Initializes Facebook Login
     */
    public void handleFacebookLogin() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        facebookLoginButton = (LoginButton) findViewById(R.id.facebook_login_button);
        facebookLoginButton.setReadPermissions("user_friends");

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                makeFbGraphRequest(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }

    /**
     * Making FB Graph Request to get FB User ID.
     * @param accessToken
     */
    void makeFbGraphRequest(final AccessToken accessToken) {
        GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                String id = object.optString("id");
                signInViaFacebook(accessToken, id);
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    /**
     * Makes an auth Call to login Via Facebook.
     * @param accessToken
     * @param id
     */
    private void signInViaFacebook(AccessToken accessToken, String id) {
        UserService.sharedInstance.loginWithFacebook(accessToken, id, new UserServiceCallback<TokenResponse>() {
            @Override
            public void onSuccess(@NonNull TokenResponse response) {
                setUserAuthToken(response.getToken());
            }

            @Override
            public void onFailure(@NonNull String errorMessage) {
                System.out.println(errorMessage);
            }
        });
    }

    /**
     * Method to Sign in using Email Id and Password.
     * @param view
     */
    public void signInViaEmail(View view) {
        EditText emailText = (EditText) findViewById(R.id.signinEmailText);
        EditText passwordText = (EditText) findViewById(R.id.signInPasswordText);

        UserService.sharedInstance.loginWithEmail(emailText.getText().toString(), passwordText.getText().toString(), new UserServiceCallback<TokenResponse>() {
            @Override
            public void onSuccess(@NonNull TokenResponse response) {
                setUserAuthToken(response.getToken());
            }

            @Override
            public void onFailure(@NonNull String errorMessage) {
                System.out.println(errorMessage);
            }
        });
    }

    /**
     * Sets User Auth Token
     * @param token - Token String
     */
    private void setUserAuthToken(String token) {
        SharedPreferencesManager manager = new SharedPreferencesManager(PreferenceManager.getDefaultSharedPreferences(getApplication()));
        manager.setUserAuthToken(token);
    }
}
