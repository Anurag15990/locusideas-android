package com.locusideas.locusideas.requests.User;

/**
 * Created by anurag on 4/29/16.
 */
public class TwitterAuthRequest {
    private String type = "twitter";
    private String accessToken;
    private String accessTokenSecret;

    public TwitterAuthRequest(String accessToken, String accessTokenSecret) {
        this.accessToken = accessToken;
        this.accessTokenSecret = accessTokenSecret;
    }
}
