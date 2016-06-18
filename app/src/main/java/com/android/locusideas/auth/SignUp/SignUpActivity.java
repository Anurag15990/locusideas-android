package com.android.locusideas.auth.SignUp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.android.locusideas.core.utils.ActivityUtils;
import com.locusideas.locusideas.R;

public class SignUpActivity extends AppCompatActivity {

    private SignUpFragment mSignUpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mSignUpFragment = (SignUpFragment) getSupportFragmentManager().findFragmentById(R.id.sign_up_content);

        if (mSignUpFragment == null){
            mSignUpFragment = new SignUpFragment();
        }

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                mSignUpFragment, R.id.sign_up_content);

    }
}
