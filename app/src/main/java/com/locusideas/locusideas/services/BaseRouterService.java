package com.locusideas.locusideas.services;

import com.locusideas.locusideas.models.UserModels.Designer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anurag on 5/1/16.
 */
public class BaseRouterService {

    private String baseUrl = "http://192.168.0.148:3002";
    private Retrofit retrofit;
    public static BaseRouterService baseRouterService = new BaseRouterService();

    public BaseRouterService() {
        retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public DesignerService createDesignerService() {
        return retrofit.create(DesignerService.class);
    }

    public UserService createUser() {
        return retrofit.create(UserService.class);
    }
}
