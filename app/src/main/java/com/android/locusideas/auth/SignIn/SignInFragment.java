package com.android.locusideas.auth.SignIn;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.android.locusideas.LocusApplication;
import com.android.locusideas.auth.SignIn.injection.DaggerSignInComponent;
import com.android.locusideas.auth.SignIn.injection.SignInModule;
import com.android.locusideas.core.data.auth.injection.AuthComponent;
import com.android.locusideas.core.data.auth.injection.AuthModule;
import com.android.locusideas.core.data.auth.injection.DaggerAuthComponent;
import com.android.locusideas.core.ui.widgets.ButtonPlus;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.locusideas.locusideas.R;
import org.json.JSONObject;
import javax.inject.Inject;

/**
 * Created on 26/05/16.
 */
public class SignInFragment extends Fragment implements SignInContract.View{

    @Inject SignInContract.Presenter mPresenter;
    private LoginButton facebookLoginButton;
    EditText emailText;
    EditText passwordText;
    ButtonPlus mSignInButton;
    private CallbackManager callbackManager;
    private AuthComponent mAuthComponent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_sign_in, container, false);

        mAuthComponent = DaggerAuthComponent.builder()
                .authModule(new AuthModule())
                .applicationComponent(((LocusApplication) getActivity().getApplication()).getApplicationComponent())
                .build();

        DaggerSignInComponent.builder()
                .signInModule(new SignInModule(this))
                .authComponent(mAuthComponent)
                .build()
                .inject(this);

        setTypeFaceForActivity(view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.signInButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onSignIn(emailText.getText().toString(), passwordText.getText().toString());
            }
        });
        handleFacebookLogin(getView());
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onDestroy() {
        //Set auth component to null to be gc
        mAuthComponent = null;
        super.onDestroy();
    }

    /**
     * Setting Fonts for the activity
     */
    public void setTypeFaceForActivity(View root) {

        Typeface montserratLight = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Montserrat-Light.otf");

        emailText = (EditText) root.findViewById(R.id.signinEmailText);
        passwordText = (EditText) root.findViewById(R.id.signInPasswordText);

        emailText.setTypeface(montserratLight);
        passwordText.setTypeface(montserratLight);

    }

    /**
     * Initializes Facebook Login
     */
    public void handleFacebookLogin(View root) {
        FacebookSdk.sdkInitialize(getActivity());
        callbackManager = CallbackManager.Factory.create();

        facebookLoginButton = (LoginButton) root.findViewById(R.id.facebook_login_button);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        callbackManager.onActivityResult(requestCode, resultCode, data);
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
        mPresenter.onSignInViaFacebook(accessToken, id);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
