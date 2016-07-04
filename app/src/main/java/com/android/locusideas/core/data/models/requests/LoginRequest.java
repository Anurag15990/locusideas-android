package com.android.locusideas.core.data.models.requests;

/**
 * Created by anurag on 4/29/16.
 */
public class LoginRequest {

    private String emailAddress;
    private String password;

    public LoginRequest(String email, String password) {
        this.emailAddress = email;
        this.password = password;
    }
}
