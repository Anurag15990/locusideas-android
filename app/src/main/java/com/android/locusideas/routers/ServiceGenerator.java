package com.android.locusideas.routers;

import com.android.locusideas.core.utils.SharedPreferencesManager;
import com.locusideas.locusideas.BuildConfig;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anurag on 5/1/16.
 */
public class ServiceGenerator {
    private static Retrofit sInstance;

    public static Retrofit getRetrofitInstance(SharedPreferencesManager sharedPreferencesManager){
        if (sInstance == null){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

            if(BuildConfig.DEBUG) {
                logging.setLevel(Level.BODY);
            } else {
                logging.setLevel(Level.NONE);
            }

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);
            httpClient.addInterceptor(new HeaderInterceptor(sharedPreferencesManager));

            sInstance = new Retrofit
                    .Builder()
                    .baseUrl(BuildConfig.BASE_API_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sInstance;
    }

    public static <S> S createService(Class <S> apiService, Retrofit retrofit){
        return retrofit.create(apiService);
    }

    static class HeaderInterceptor implements okhttp3.Interceptor{

        SharedPreferencesManager sharedPreferencesManager;

        public HeaderInterceptor(SharedPreferencesManager sharedPreferencesManager){
            this.sharedPreferencesManager = sharedPreferencesManager;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder();
            String authToken = sharedPreferencesManager.getUserAuthToken();

            requestBuilder.header("Content-Type", "application/json");

            //TODO seperate request which need auth token
            if (authToken != null){
                requestBuilder.header("Authorization", "Bearer " + sharedPreferencesManager.getUserAuthToken());
            }

            Request request = requestBuilder.build();
            return chain.proceed(request);
        }
    }


}
