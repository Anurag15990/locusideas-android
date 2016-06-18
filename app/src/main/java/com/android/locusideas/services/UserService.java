package com.android.locusideas.services;

import com.android.locusideas.requests.user.FacebookAuthRequest;
import com.android.locusideas.requests.user.LoginRequest;
import com.android.locusideas.requests.user.RegisterRequest;
import com.android.locusideas.responses.TokenResponse;
import com.android.locusideas.routers.ServiceGenerator;
import com.android.locusideas.routers.UserRouter;
import com.facebook.AccessToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        Call<TokenResponse> loginAuthCall = ServiceGenerator.createService(UserRouter.class, ServiceGenerator.getRetrofitInstance())
                .login(loginRequest);

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
        Call<TokenResponse> facebookAuthCall = ServiceGenerator.createService(UserRouter.class, ServiceGenerator.getRetrofitInstance())
                .facebookAuth(facebookAuthRequest);

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

    /**
     * Method to Register a User. Takes in User Email & Password to register the user.
     * @param email - Email Id of the user.
     * @param password - Password of the user.
     * @param callback - Returns tokenResponse on success else return error Message
     */
    public void register(String email, String password, final UserServiceCallback<TokenResponse> callback) {
        RegisterRequest registerRequest = new RegisterRequest(email, password);
        Call<TokenResponse> registerCall = ServiceGenerator.createService(UserRouter.class, ServiceGenerator.getRetrofitInstance())
                .register(registerRequest);

        registerCall.enqueue(new Callback<TokenResponse>() {
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
