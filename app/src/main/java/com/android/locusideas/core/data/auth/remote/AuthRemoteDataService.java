package com.android.locusideas.core.data.auth.remote;

import com.android.locusideas.core.data.auth.AuthDataContract;
import com.android.locusideas.core.data.coreServices.UserService;
import com.android.locusideas.core.data.models.ApiError;
import com.android.locusideas.requests.user.FacebookAuthRequest;
import com.android.locusideas.requests.user.LoginRequest;
import com.android.locusideas.responses.TokenResponse;
import com.android.locusideas.routers.ServiceGenerator;
import com.facebook.AccessToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on 28/05/16.
 */
public class AuthRemoteDataService {

    AuthDataContract.Callbacks mAuthDataCallbacks;

    public void setAuthDataCallbacks(AuthDataContract.Callbacks authDataCallbacks){
        mAuthDataCallbacks = authDataCallbacks;
    }

    public void signInWithEmailId(String emailId, String password){
        LoginRequest loginRequest = new LoginRequest(emailId, password);

        Call<TokenResponse> loginAuthCall = ServiceGenerator.createService(UserService.class, ServiceGenerator.getRetrofitInstance())
                .login(loginRequest);

        loginAuthCall.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                TokenResponse tokenResponse = response.body();
                if (response.isSuccessful()){
                    mAuthDataCallbacks.onSignInSuccess(tokenResponse.getToken());
                    return;
                }
                //TODO update APIERROR class
                mAuthDataCallbacks.onSignInFailure(new ApiError(response.message()));
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                System.out.println(t.getLocalizedMessage());
                //TODO update APIERROR class
                //TODO request failed due to network error
                //Write retry logic
                mAuthDataCallbacks.onSignInFailure(new ApiError(t.getMessage()));
            }
        });
    }

    public void signInViaFacebook(AccessToken accessToken, String fbId){
        FacebookAuthRequest facebookAuthRequest = new FacebookAuthRequest(accessToken, fbId);
        Call<TokenResponse> facebookAuthCall = ServiceGenerator.createService(UserService.class, ServiceGenerator.getRetrofitInstance())
                .facebookAuth(facebookAuthRequest);

        facebookAuthCall.enqueue(new retrofit2.Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                TokenResponse tokenResponse = response.body();
                mAuthDataCallbacks.onSignInSuccess(tokenResponse.getToken());
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                mAuthDataCallbacks.onSignInFailure(new ApiError(t.getMessage()));
            }
        });
    }

}
