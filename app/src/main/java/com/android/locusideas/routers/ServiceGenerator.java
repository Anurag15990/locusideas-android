package com.android.locusideas.routers;

import com.locusideas.locusideas.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anurag on 5/1/16.
 */
public class ServiceGenerator {
    private static Retrofit sInstance;

    public static Retrofit getRetrofitInstance(){
        if (sInstance == null){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

            if(BuildConfig.DEBUG) {
                logging.setLevel(Level.BODY);
            } else {
                logging.setLevel(Level.NONE);
            }

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);

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

}
