package com.android.locusideas.core.data.models.responses;

/**
 * Created by anurag on 4/29/16.
 */
public class TokenResponse {
    private String token;

    public TokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
