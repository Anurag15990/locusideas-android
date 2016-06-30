package com.android.locusideas.auth.signUp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.locusideas.LocusApplication;
import com.android.locusideas.auth.signUp.injection.DaggerSignUpComponent;
import com.android.locusideas.auth.signUp.injection.SignUpModule;
import com.android.locusideas.core.data.auth.injection.AuthComponent;
import com.android.locusideas.core.data.auth.injection.AuthModule;
import com.android.locusideas.core.data.auth.injection.DaggerAuthComponent;
import com.android.locusideas.home.MainShellActivity;
import com.locusideas.locusideas.R;

import javax.inject.Inject;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by satyaiyengar on 14/06/16.
 */

public class SignUpFragment extends Fragment implements SignUpContract.View {

//    @BindView(R.id.signUpEmailText)
    EditText mEmailEditText;

//    @BindView(R.id.signUpPasswordText)
    EditText mPasswordEditText;

//    @BindView(R.id.signUpConfirmText)
    EditText mConfirmPasswordEditText;

    @Inject SignUpContract.Presenter mPresenter;

    private AuthComponent mAuthComponent;

    ProgressDialog pd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_sign_up, container, false);
        //ButterKnife.bind(this, view);
        mEmailEditText = (EditText)view.findViewById(R.id.signUpEmailText);
        mPasswordEditText = (EditText)view.findViewById(R.id.signUpPasswordText);
        mConfirmPasswordEditText = (EditText)view.findViewById(R.id.signUpConfirmText);

        mAuthComponent = DaggerAuthComponent.builder()
                .authModule(new AuthModule())
                .applicationComponent(((LocusApplication) getActivity().getApplication()).getApplicationComponent())
                .build();

        DaggerSignUpComponent.builder()
                .authComponent(mAuthComponent)
                .signUpModule(new SignUpModule(this))
                .build()
                .inject(this);

        view.findViewById(R.id.signUpButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSignUp();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCustomFonts();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAuthComponent = null;
    }

    //    @OnClick(R.id.signUpButton)
    public void onClickSignUp(){
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        String confirmPassword = mConfirmPasswordEditText.getText().toString();

        if(!confirmPassword.equals(password)){
            /**
             * Showing Toast to inform user that password and confirm text does not match.
             */
            Toast toast = Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
            return;
        }

        mPresenter.onClickSignUp(email, password);
    }

    public void setCustomFonts() {
        Typeface montserratLight = Typeface.createFromAsset(getActivity().getAssets(), getResources().getString(R.string.font_montserrat_light));

        EditText nameText = (EditText) getView().findViewById(R.id.signUpNameText);
        EditText emailText = (EditText) getView().findViewById(R.id.signUpEmailText);
        EditText passwordText = (EditText) getView().findViewById(R.id.signUpPasswordText);
        EditText confirmText = (EditText) getView().findViewById(R.id.signUpConfirmText);

        nameText.setTypeface(montserratLight);
        emailText.setTypeface(montserratLight);
        passwordText.setTypeface(montserratLight);
        confirmText.setTypeface(montserratLight);

        Typeface montserratExtraBold = Typeface.createFromAsset(getActivity().getAssets(), getResources().getString(R.string.font_montserrat_extrabold));

        Button signInButton = (Button) getView().findViewById(R.id.signUpButton);
        signInButton.setTypeface(montserratExtraBold);

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showLoader() {
        pd = ProgressDialog.show(getActivity(), "Signup in progress", "Please wait", true);
    }

    @Override
    public void hideLoader() {
        pd.dismiss();
    }

    @Override
    public void navigateToMainActivity() {
        Intent mainActivityIntent = new Intent(getActivity().getApplicationContext(), MainShellActivity.class);
        mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getActivity().startActivity(mainActivityIntent);
        getActivity().finish();
    }
}
