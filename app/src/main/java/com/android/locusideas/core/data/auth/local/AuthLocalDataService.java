package com.android.locusideas.core.data.auth.local;

import com.android.locusideas.LocusApplication;

/**
 * Created on 29/05/16.
 */
public class AuthLocalDataService {

    LocusApplication mApplication;

    public AuthLocalDataService(LocusApplication application){
        mApplication = application;
    }

    public void saveUserToken(String token){
        mApplication.getSharedPreferencesManager()
                .setUserAuthToken(token);
    }

}
