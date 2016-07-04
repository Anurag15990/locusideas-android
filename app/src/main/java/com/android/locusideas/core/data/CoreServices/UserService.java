package com.android.locusideas.core.data.coreServices;

import com.android.locusideas.core.data.models.requests.FacebookAuthRequest;
import com.android.locusideas.core.data.models.requests.LoginRequest;
import com.android.locusideas.core.data.models.requests.RegisterRequest;
import com.android.locusideas.core.data.models.responses.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by satyaiyengar on 13/06/16.
 */

public interface UserService {

    @Headers({"Content-Type: application/json"})
    @POST("/api/users/register")
    Call<TokenResponse> register(@Body RegisterRequest registerRequest);

    @Headers({"Content-Type: application/json"})
    @POST("/api/users/login")
    Call<TokenResponse> login(@Body LoginRequest loginRequest);

    @Headers({"Content-Type: application/json"})
    @POST("/api/users/auth")
    Call<TokenResponse> facebookAuth(@Body FacebookAuthRequest facebookAuthRequest);
}
