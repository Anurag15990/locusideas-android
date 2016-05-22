package com.locusideas.locusideas.requests.user;

import com.facebook.AccessToken;

/**
 * Created by anurag on 4/29/16.
 */
public class FacebookAuthRequest {

    private String type = "facebook";
    private String accessToken;
    private String id;

    public FacebookAuthRequest(AccessToken accessToken, String id) {
        this.accessToken = accessToken.getToken();
        this.id = id;
    }
}
