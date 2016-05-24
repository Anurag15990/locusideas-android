package com.android.locusideas.requests.user;

/**
 * Created by anurag on 4/29/16.
 */
public class LoginRequest {

    private String email;
    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
