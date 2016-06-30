package com.android.locusideas.SplashScreen;

import com.android.locusideas.core.ui.BasePresenter;
import com.android.locusideas.core.ui.BaseView;

/**
 * Created on 30/06/16.
 */
public interface SplashScreenContract{

    interface View extends BaseView{

        void navigateToMainActivity();

        void navigateToAuthActivity();
    }

    interface Presenter extends BasePresenter{

    }
}
