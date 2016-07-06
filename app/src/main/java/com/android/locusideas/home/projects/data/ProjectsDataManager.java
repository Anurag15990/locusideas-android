package com.android.locusideas.home.projects.data;

import com.android.locusideas.core.data.models.ApiError;
import com.android.locusideas.core.data.models.response.ProjectsResponse;
import com.android.locusideas.core.utils.ApiCallback;
import com.android.locusideas.home.projects.ProjectsPresenter;
import com.android.locusideas.home.projects.data.remote.ProjectsRemoteDataService;

/**
 * Created on 28/06/16.
 */

public class ProjectsDataManager implements ProjectsDataSource {

    ProjectsPresenter projectsPresenter;
    ProjectsRemoteDataService projectsRemoteDataService;

    public ProjectsDataManager(ProjectsRemoteDataService projectsRemoteDataService){
        this.projectsRemoteDataService = projectsRemoteDataService;
    }

    @Override
    public void setProjectsPresenter(ProjectsPresenter projectsPresenter){
        this.projectsPresenter = projectsPresenter;
    }

    @Override
    public void likeProjectWithId(String projectId) {

    }

    @Override
    public void loadProjects(){
        projectsRemoteDataService.loadProjects(new LoadDesignsCallback(this));
    }

    private static class LoadDesignsCallback implements ApiCallback<ProjectsResponse> {
        ProjectsDataManager projectsDataManager;

        public LoadDesignsCallback(ProjectsDataManager projectsDataManager){
            this.projectsDataManager = projectsDataManager;
        }

        @Override
        public void onSuccess(ProjectsResponse result) {
            projectsDataManager.projectsPresenter.onProjectsLoaded(result.getProjects());
        }

        @Override
        public void onFailure(ApiError error) {
            projectsDataManager.projectsPresenter.showError(error.getMessage());
        }
    }

}
