package com.android.locusideas.core.data.services;

import com.android.locusideas.core.data.models.response.ProjectsResponse;
import com.android.locusideas.home.projects.models.ProjectMediaResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created on 13/06/16.
 */

public interface ProjectsService {

    @GET("/api/projects")
    Call<ProjectsResponse> loadProjects();

    @GET("/api/projects/{projectId}/medias")
    Call<ProjectMediaResponse> loadProjectMedias(@Path("projectId")String projectId);

}
