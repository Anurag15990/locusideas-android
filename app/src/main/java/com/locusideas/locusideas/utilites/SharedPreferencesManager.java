package com.locusideas.locusideas.utilites;

import android.content.SharedPreferences;

/**
 * Created by anurag on 5/7/16.
 */

/*
* Shared Preferences Manager to store User Data.
*/

public class SharedPreferencesManager {

    private static final String isUserSignedIn = "isUserSignedIn";
    private static final String userAuthToken = "userAuthToken";
    private static final String userObject = "userObject";

    private final SharedPreferences sharedPreferences;

    public SharedPreferencesManager(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void setIsUserSignedIn(boolean signedIn) {
        edit().putBoolean(isUserSignedIn, signedIn).apply();
    }

    public boolean isUserSignedIn() {
        return sharedPreferences.getBoolean(isUserSignedIn, false);
    }

    public void setUserAuthToken(String authToken) {
        edit().putString(userAuthToken, authToken).apply();
    }

    public String getUserAuthToken() {
        return sharedPreferences.getString(userAuthToken, null);
    }

    public void setUserObject(String user) {
        edit().putString(userObject, user).apply();
    }

    public String getUserObject() {
        return sharedPreferences.getString(userObject, null);
    }

    public void clearPreferences() {
        edit().remove(isUserSignedIn)
                .remove(userAuthToken)
                .remove(userObject);
    }

    private SharedPreferences.Editor edit() {
        return sharedPreferences.edit();
    }
}
