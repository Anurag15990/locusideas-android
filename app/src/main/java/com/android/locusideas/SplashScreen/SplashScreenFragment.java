package com.android.locusideas.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.locusideas.LocusApplication;
import com.android.locusideas.SplashScreen.injection.DaggerSplashScreenComponent;
import com.android.locusideas.SplashScreen.injection.SplashScreenComponent;
import com.android.locusideas.SplashScreen.injection.SplashScreenModule;
import com.android.locusideas.auth.AuthActivity;
import com.android.locusideas.home.MainShellActivity;
import com.locusideas.locusideas.R;
import javax.inject.Inject;

/**
 * Created on 30/06/16.
 */
public class SplashScreenFragment extends Fragment implements SplashScreenContract.View{

    @Inject
    SplashScreenContract.Presenter presenter;

    SplashScreenComponent splashScreenComponent;
    Handler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_screen, container, false);

        splashScreenComponent = DaggerSplashScreenComponent.builder().applicationComponent(((LocusApplication)getActivity().getApplicationContext()).getApplicationComponent())
                .splashScreenModule(new SplashScreenModule(this))
                .build();

        splashScreenComponent.inject(this);
        handler = new Handler();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        splashScreenComponent = null;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void navigateToMainActivity(){
        handler.postDelayed(new NavigatationRunnable(this, new Intent(getActivity(), MainShellActivity.class)), 2000);
    }

    @Override
    public void navigateToAuthActivity(){
        handler.postDelayed(new NavigatationRunnable(this, new Intent(getActivity(), AuthActivity.class)), 2000);
    }

    private static class NavigatationRunnable implements Runnable{

        Intent nextActivity;
        Fragment currentFragment;

        public NavigatationRunnable(Fragment currentFragment, Intent nextActivity){
            this.nextActivity = nextActivity;
            this.currentFragment = currentFragment;
        }

        @Override
        public void run() {
            if(currentFragment.isAdded()){
                currentFragment.getActivity().startActivity(nextActivity);
                currentFragment.getActivity().finish();
            }
            currentFragment = null;
        }

    }

}
