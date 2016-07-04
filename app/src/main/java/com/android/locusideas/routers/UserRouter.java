package com.android.locusideas.routers;

import com.android.locusideas.core.data.models.requests.FacebookAuthRequest;
import com.android.locusideas.core.data.models.requests.FollowRequest;
import com.android.locusideas.core.data.models.requests.LoginRequest;
import com.android.locusideas.core.data.models.requests.RegisterRequest;
import com.android.locusideas.core.data.models.requests.UnFollowRequest;
import com.android.locusideas.core.data.models.responses.TokenResponse;
import com.android.locusideas.models.user.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by anurag on 4/26/16.
 */
public interface UserRouter {

    @GET("/users/me")
    Call<User> getMe();

    @GET("/users")
    Call<List<User>> getUsers();

    @Headers({"Content-Type: application/json"})
    @POST("/api/users/auth")
    Call<TokenResponse> facebookAuth(@Body FacebookAuthRequest facebookAuthRequest);

    @POST("/users")
    Call<User> updateUser(@Body User user);

    @POST("/api/users/follow")
    Call followUser(@Body FollowRequest followRequest);

    @POST("/users/unfollow")
    Call unFollowUser(@Body UnFollowRequest unFollowRequest);

    @Headers({"Content-Type: application/json"})
    @POST("/api/users/register")
    Call<TokenResponse> register(@Body RegisterRequest registerRequest);

    @Headers({"Content-Type: application/json"})
    @POST("/api/users/login")
    Call<TokenResponse> login(@Body LoginRequest loginRequest);
}
