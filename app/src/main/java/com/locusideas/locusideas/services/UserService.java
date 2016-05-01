package com.locusideas.locusideas.services;

import com.locusideas.locusideas.Requests.User.FacebookAuthRequest;
import com.locusideas.locusideas.Requests.User.FollowRequest;
import com.locusideas.locusideas.Requests.User.LoginRequest;
import com.locusideas.locusideas.Requests.User.RegisterRequest;
import com.locusideas.locusideas.Requests.User.TwitterAuthRequest;
import com.locusideas.locusideas.Requests.User.UnFollowRequest;
import com.locusideas.locusideas.Responses.TokenResponse;
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
public interface UserService {

    @GET("/users/me")
    Call<User> getMe();

    @GET("/users")
    Call<List<User>> getUsers();

    @Headers({"Content-Type: application/json"})
    @POST("/api/users/auth")
    Call<TokenResponse> facebookAuth(@Body FacebookAuthRequest facebookAuthRequest);

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

    @POST("/users/login")
    Call<TokenResponse> login(@Body LoginRequest loginRequest);
}
