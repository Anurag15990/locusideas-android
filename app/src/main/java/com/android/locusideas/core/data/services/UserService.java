package com.android.locusideas.core.data.services;

import com.android.locusideas.core.data.models.requests.FacebookAuthRequest;
import com.android.locusideas.core.data.models.requests.LoginRequest;
import com.android.locusideas.core.data.models.requests.RegisterRequest;
import com.android.locusideas.core.data.models.response.TokenResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created on 13/06/16.
 */

public interface UserService {

    @POST("/api/users/register")
    Call<TokenResponse> register(@Body RegisterRequest registerRequest);

    @POST("/api/users/login")
    Call<TokenResponse> login(@Body LoginRequest loginRequest);

    @POST("/api/users/auth")
    Call<TokenResponse> facebookAuth(@Body FacebookAuthRequest facebookAuthRequest);
}
