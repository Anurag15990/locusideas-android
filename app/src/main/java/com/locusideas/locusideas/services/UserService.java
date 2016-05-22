package com.locusideas.locusideas.services;

import com.facebook.AccessToken;
import com.locusideas.locusideas.requests.user.FacebookAuthRequest;
import com.locusideas.locusideas.responses.TokenResponse;
import com.locusideas.locusideas.routers.BaseRouterService;
import com.locusideas.locusideas.requests.user.LoginRequest;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;


/**
 * Class for Handling all User Related activity.
 */
public class UserService {

    /**
     * Singleton Instance for User Service Class.
     */
    public static UserService sharedInstance = new UserService();

    /**
     * User Service Constructor
     */
    public UserService() {}

    /**
     * Method that makes request to Login via EmailId & Password
     * @param emailId - Email ID of the user
     * @param password - Password of the user
     * @param callback - Returns tokenResponse on success else return error Message
     */
    public void loginWithEmail(String emailId, String password, final UserServiceCallback<TokenResponse> callback) {
        LoginRequest loginRequest = new LoginRequest(emailId, password);
        Call<TokenResponse> loginAuthCall = BaseRouterService.baseRouterService.createUser().login(loginRequest);
        loginAuthCall.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                TokenResponse tokenResponse = response.body();
                callback.onSuccess(tokenResponse);
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                System.out.println(t.getLocalizedMessage());
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }

    /**
     * Method that makes request to login via Facebook
     * @param accessToken - Facebook Auth Token
     * @param id - Facebook ID
     * @param callback - Returns tokenResponse on success else returns errorMessage
     */
    public void loginWithFacebook(AccessToken accessToken, String id, final UserServiceCallback<TokenResponse> callback) {
        FacebookAuthRequest facebookAuthRequest = new FacebookAuthRequest(accessToken, id);
        Call<TokenResponse> facebookAuthCall = BaseRouterService.baseRouterService.createUser().facebookAuth(facebookAuthRequest);
        facebookAuthCall.enqueue(new retrofit2.Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                TokenResponse tokenResponse = response.body();
                callback.onSuccess(tokenResponse);
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }

}
