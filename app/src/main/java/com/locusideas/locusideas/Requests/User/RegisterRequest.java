package com.locusideas.locusideas.Requests.User;

/**
 * Created by anurag on 4/29/16.
 */
public class RegisterRequest {

    private String email;
    private String password;

    public RegisterRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
