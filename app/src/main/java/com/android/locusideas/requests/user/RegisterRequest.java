package com.android.locusideas.requests.user;

/**
 * Created by anurag on 4/29/16.
 */
public class RegisterRequest {

    private String emailAddress;
    private String password;

    public RegisterRequest(String email, String password) {
        this.emailAddress = email;
        this.password = password;
    }
}
