package com.locusideas.locusideas.services;

import com.locusideas.locusideas.models.UserModels.Designer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by anurag on 4/25/16.
 */

public interface DesignerService {

    @POST("/designers")
    Call<Designer> createDesigner(@Body Designer designer);

    @PUT("/designers/{designerId}")
    Call<Designer> updateDesigner(@Path("designerId") String designerId, @Body Designer designer);

    @GET("/designers/{designerId}")
    Call<Designer> getDesigner(@Path("designerId") String designerId);

}