package com.locusideas.locusideas.routers;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anurag on 5/1/16.
 */
public class BaseRouterService {

    private String baseUrl = "http://api-staging-c3po.locusideas.com";
    private Retrofit retrofit;
    public static BaseRouterService baseRouterService = new BaseRouterService();

    public BaseRouterService() {
        retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public DesignerRouter createDesignerService() {
        return retrofit.create(DesignerRouter.class);
    }

    public UserRouter createUser() {
        return retrofit.create(UserRouter.class);
    }
}
