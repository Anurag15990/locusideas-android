package com.locusideas.locusideas.Requests.User;

/**
 * Created by anurag on 4/29/16.
 */
public class FacebookAuthRequest {

    private String type = "facebook";
    private String accessToken;
    private String id;

    public FacebookAuthRequest(String accessToken, String id) {
        this.accessToken = accessToken;
        this.id = id;
    }
}
