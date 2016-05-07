package com.locusideas.locusideas.auth;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.content.Intent;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.locusideas.locusideas.Requests.User.FacebookAuthRequest;
import com.locusideas.locusideas.Requests.User.LoginRequest;
import com.locusideas.locusideas.Requests.User.TwitterAuthRequest;
import com.locusideas.locusideas.Responses.TokenResponse;
import com.locusideas.locusideas.services.BaseRouterService;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.identity.*;
import com.twitter.sdk.android.core.*;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import org.json.JSONObject;

import com.locusideas.locusideas.R;

import retrofit2.*;

public class SignInActivity extends AppCompatActivity {

    private TwitterLoginButton twitterLoginButton;
    private LoginButton facebookLoginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        setTypeFaceForActivity();
        handleTwitterLoginForSignIn();
        handleFacebookLogin();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
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
    }

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

    void makeFbGraphRequest(final AccessToken accessToken) {
        GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                String id = object.optString("id");
                FacebookAuthRequest facebookAuthRequest = new FacebookAuthRequest(accessToken, id);
                Call<TokenResponse> facebookAuthCall = BaseRouterService.baseRouterService.createUser().facebookAuth(facebookAuthRequest);
                facebookAuthCall.enqueue(new retrofit2.Callback<TokenResponse>() {
                    @Override
                    public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                        System.out.println("Reached Success Callback Function");
                        TokenResponse tokenResponse = response.body();
                        System.out.println(tokenResponse.getToken());

                    }

                    @Override
                    public void onFailure(Call<TokenResponse> call, Throwable t) {
                        System.out.println("Reached Error Callback Function");
                        System.out.println(t.getStackTrace());
                    }
                });
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    public void handleTwitterLoginForSignIn() {
        twitterLoginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                TwitterSession session = result.data;
                TwitterAuthRequest authRequest = new TwitterAuthRequest(session.getAuthToken().token, session.getAuthToken().secret);
                Call<TokenResponse> twitterAuthCall = BaseRouterService.baseRouterService.createUser().twitterAuth(authRequest);
                twitterAuthCall.enqueue(new retrofit2.Callback<TokenResponse>() {
                    @Override
                    public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                        System.out.println("Entered Success block");
                        TokenResponse tokenResponse = response.body();
                        System.out.println(tokenResponse.getToken());
                    }

                    @Override
                    public void onFailure(Call<TokenResponse> call, Throwable t) {
                        System.out.println("Entered Error block");
                        System.out.println(t.getLocalizedMessage());
                    }
                });
                requestTwitterEmailId(session);
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

    public void signInViaEmail(View view) {
        EditText emailText = (EditText) findViewById(R.id.signinEmailText);
        EditText passwordText = (EditText) findViewById(R.id.signInPasswordText);

        loginWithEmail(emailText.getText().toString(), passwordText.getText().toString());
    }

    public void loginWithEmail(String emailId, String password) {
        LoginRequest loginRequest = new LoginRequest(emailId, password);
        Call<TokenResponse> loginAuthCall = BaseRouterService.baseRouterService.createUser().login(loginRequest);
        loginAuthCall.enqueue(new retrofit2.Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                System.out.println("Entered Success Callback");
                TokenResponse tokenResponse = response.body();
                System.out.println(tokenResponse.getToken());
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                System.out.println("Entered Error Callback");
                System.out.println(t.getLocalizedMessage());
            }
        });
    }

}
