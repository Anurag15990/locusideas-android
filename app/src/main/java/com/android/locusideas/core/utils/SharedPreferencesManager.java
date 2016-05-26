package com.android.locusideas.core.utils;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by anurag on 5/7/16.
 */

/*
* Shared Preferences Manager to store User Data.
*/

public class SharedPreferencesManager {

    //TODO Anurag check
    //There is no benefit of keeping these strings static as there will be helper methods for each preference stored
    private static final String IS_USER_SIGNED_IN = "isUserSignedIn";
    private static final String USER_AUTH_TOKEN = "userAuthToken";
    private static final String USER_STRING = "userString";

    private final SharedPreferences sharedPreferences;

    @Inject
    public SharedPreferencesManager(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    //TODO is this needed to be stored as seperate preference
    //cant be figured out using the presence of auth token?
    public void setIsUserSignedIn(boolean signedIn) {
        this.editor()
            .putBoolean(IS_USER_SIGNED_IN, signedIn)
            .apply();
    }

    public boolean isUserSignedIn() {
        return sharedPreferences.getBoolean(IS_USER_SIGNED_IN, false);
    }

    public void setUserAuthToken(String authToken) {
        this.editor()
            .putString(USER_AUTH_TOKEN, authToken)
            .apply();
    }

    public String getUserAuthToken() {
        return sharedPreferences.getString(USER_AUTH_TOKEN, null);
    }

    public void setUserString(String userString) {
        this.editor()
            .putString(USER_STRING, userString)
            .apply();
    }

    public String getUserString() {
        return sharedPreferences.getString(USER_STRING, null);
    }

    public void clearPreferences() {
        this.editor()
            .clear()
            .commit();
    }

    private SharedPreferences.Editor editor() {
        return sharedPreferences.edit();
    }
}
