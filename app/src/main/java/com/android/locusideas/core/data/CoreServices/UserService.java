package com.android.locusideas.core.data.CoreServices;

import com.android.locusideas.requests.user.LoginRequest;
import com.android.locusideas.responses.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by satyaiyengar on 13/06/16.
 */

public interface UserService {
    @Headers({"Content-Type: application/json"})
    @POST("/api/users/login")
    Call<TokenResponse> login(@Body LoginRequest loginRequest);
}
