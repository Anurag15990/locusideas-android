package com.locusideas.locusideas.routers;

import com.locusideas.locusideas.requests.user.FacebookAuthRequest;
import com.locusideas.locusideas.requests.user.FollowRequest;
import com.locusideas.locusideas.requests.user.LoginRequest;
import com.locusideas.locusideas.requests.user.RegisterRequest;
import com.locusideas.locusideas.requests.user.TwitterAuthRequest;
import com.locusideas.locusideas.requests.user.UnFollowRequest;
import com.locusideas.locusideas.responses.TokenResponse;
import com.locusideas.locusideas.models.UserModels.User;

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

    @Headers({"Content-Type: application/json"})
    @POST("/api/users/auth")
    Call<TokenResponse> twitterAuth(@Body TwitterAuthRequest twitterAuthRequest);

    @POST("/users")
    Call<User> updateUser(@Body User user);

    @POST("/api/users/follow")
    Call followUser(@Body FollowRequest followRequest);

    @POST("/users/unfollow")
    Call unFollowUser(@Body UnFollowRequest unFollowRequest);

    @POST("/users/register")
    Call<TokenResponse> register(@Body RegisterRequest registerRequest);

    @Headers({"Content-Type: application/json"})
    @POST("/api/users/login")
    Call<TokenResponse> login(@Body LoginRequest loginRequest);
}
