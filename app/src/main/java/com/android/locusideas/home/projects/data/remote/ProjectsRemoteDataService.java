package com.android.locusideas.home.projects.data.remote;

import com.android.locusideas.core.data.models.ApiError;
import com.android.locusideas.core.data.services.ProjectsService;
import com.android.locusideas.core.utils.ApiCallback;
import com.android.locusideas.core.utils.SharedPreferencesManager;
import com.android.locusideas.core.data.models.response.ProjectsResponse;
import com.android.locusideas.routers.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on 30/06/16.
 */
public class ProjectsRemoteDataService {

    SharedPreferencesManager sharedPreferencesManager;
    ProjectsService projectsService;

    public ProjectsRemoteDataService(SharedPreferencesManager sharedPreferencesManager){
        this.sharedPreferencesManager = sharedPreferencesManager;
        projectsService = ServiceGenerator.createService(ProjectsService.class, ServiceGenerator.getRetrofitInstance(sharedPreferencesManager));
    }

    public void loadProjects(final ApiCallback<ProjectsResponse> callback){
        Call<ProjectsResponse> loadProjectsCall = projectsService.loadProjects();
        loadProjectsCall.enqueue(new Callback<ProjectsResponse>() {
            @Override
            public void onResponse(Call<ProjectsResponse> call, Response<ProjectsResponse> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ProjectsResponse> call, Throwable t) {
                callback.onFailure(new ApiError(t.getMessage()));
            }
        });
    }

}
