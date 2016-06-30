package com.android.locusideas.core.data.services;

import com.android.locusideas.requests.user.FacebookAuthRequest;
import com.android.locusideas.requests.user.LoginRequest;
import com.android.locusideas.requests.user.RegisterRequest;
import com.android.locusideas.responses.TokenResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by satyaiyengar on 13/06/16.
 */

public interface UserService {

    @POST("/api/users/register")
    Call<TokenResponse> register(@Body RegisterRequest registerRequest);

    @POST("/api/users/login")
    Call<TokenResponse> login(@Body LoginRequest loginRequest);

    @POST("/api/users/auth")
    Call<TokenResponse> facebookAuth(@Body FacebookAuthRequest facebookAuthRequest);
}
