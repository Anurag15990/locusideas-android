package com.android.locusideas.auth;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Gravity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.locusideas.locusideas.R;
import com.android.locusideas.responses.TokenResponse;
import com.android.locusideas.services.UserService;
import com.android.locusideas.services.UserServiceCallback;
import com.android.locusideas.utilites.SharedPreferencesManager;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.FacebookException;

import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    private LoginButton facebookLoginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTypeFaceForActivity();
        handleFacebookLogin();
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

    }


    /**
     * Initializes Facebook Login
     */
    public void handleFacebookLogin() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        facebookLoginButton = (LoginButton) findViewById(R.id.facebook_login_button_signup);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        callbackManager.onActivityResult(requestCode, resultCode, data);
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

    public void registerViaEmail(View view) {
        // TODO: Check if Email ID is Valid.
        EditText emailText = (EditText) findViewById(R.id.signUpEmailText);
        EditText passwordText = (EditText) findViewById(R.id.signUpPasswordText);
        EditText confirmText = (EditText) findViewById(R.id.signUpConfirmText);

        if (this.comparePasswordText(passwordText.getText().toString(), confirmText.getText().toString())) {
            UserService.sharedInstance.register(emailText.getText().toString(),
                    passwordText.getText().toString(),
                    new UserServiceCallback<TokenResponse>() {
                        @Override
                        public void onSuccess(@NonNull TokenResponse response) {
                            setUserAuthToken(response.getToken());
                        }

                        @Override
                        public void onFailure(@NonNull String errorMessage) {
                            System.out.println(errorMessage);
                        }
                    });
        } else {
            /**
             * Showing Toast to inform user that password and confirm text does not match.
             */
            Toast toast = Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        }

    }

    /**
     * Method to check if Confirmed Text Field Matches with Password Text Field.
     * @param passwordText
     * @param confirmText
     * @return - True if both strings are equal else returns false.
     */
    private boolean comparePasswordText(String passwordText, String confirmText) {
        if (passwordText.equals(confirmText)) {
            return true;
        }
        return false;
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
