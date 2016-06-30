package com.android.locusideas.auth.SignUp;

import com.android.locusideas.core.ui.BasePresenter;
import com.android.locusideas.core.ui.BaseView;

/**
 * Created on 26/05/16.
 */
public interface SignUpContract {

    interface Presenter extends BasePresenter{
        void onClickSignUp(String email, String password);
    }

    interface View extends BaseView{
        void showLoader();

        void hideLoader();

        void navigateToMainActivity();
    }
}
