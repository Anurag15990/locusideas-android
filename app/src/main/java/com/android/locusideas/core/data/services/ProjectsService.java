package com.android.locusideas.core.data.services;

import com.android.locusideas.core.data.models.response.ProjectsResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by satyaiyengar on 13/06/16.
 */

public interface ProjectsService {

    @GET("/api/projects")
    Call<ProjectsResponse> loadProjects();

}
