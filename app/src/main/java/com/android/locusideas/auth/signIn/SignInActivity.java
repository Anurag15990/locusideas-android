package com.android.locusideas.auth.signIn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.android.locusideas.core.utils.ActivityUtils;
import com.locusideas.locusideas.R;

public class SignInActivity extends AppCompatActivity {

    private SignInFragment signInFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signInFragment = (SignInFragment)getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (signInFragment == null){
            signInFragment = new SignInFragment();
        }

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                signInFragment, R.id.contentFrame);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        signInFragment.onActivityResult(requestCode, resultCode, data);
    }

}
