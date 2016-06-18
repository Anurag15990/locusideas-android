package com.android.locusideas.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.locusideas.adapter.CustomPagerAdapter;
import com.android.locusideas.auth.SignIn.SignInActivity;
import com.android.locusideas.auth.SignUp.SignUpActivity;
import com.android.locusideas.auth.injection.AuthModule;
import com.android.locusideas.auth.injection.DaggerAuthComponent;
import com.locusideas.locusideas.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by satyaiyengar on 14/06/16.
 */

public class AuthFragment extends Fragment implements AuthContract.View{

    @BindView(R.id.pager)
    ViewPager mViewPager;

    @Inject
    AuthContract.Presenter mPresenter;

    int[] mResources = {
            R.drawable.splash_1,
            R.drawable.splash_2,
            R.drawable.splash_3,
            R.drawable.splash_4
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auth, container, false);
        ButterKnife.bind(this, view);
        DaggerAuthComponent.builder()
                .authModule(new AuthModule(this))
                .build()
                .inject(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            CustomPagerAdapter mCustomPagerAdapter = new CustomPagerAdapter(getActivity().getApplicationContext(), mResources);
            mViewPager.setAdapter(mCustomPagerAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.signInButton)
    public void onClickSignIn(){
        mPresenter.onClickSignIn();
    }

    @Override
    public void openSignInActivity(){
        Intent signInIntent = new Intent(getActivity(), SignInActivity.class);
        startActivity(signInIntent);
    }

    @OnClick(R.id.signUpButton)
    public void onClickSignUp(){
        mPresenter.onCLickSignUp();
    }

    @Override
    public void openSignUpActivity(){
        Intent signUpIntent = new Intent(getActivity(), SignUpActivity.class);
        startActivity(signUpIntent);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
