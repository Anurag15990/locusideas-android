package com.android.locusideas.home.projects.project;

import com.android.locusideas.core.data.models.ApiError;
import com.android.locusideas.core.utils.ApiCallback;
import com.android.locusideas.core.utils.mvp.BasePresenter;
import com.android.locusideas.home.projects.data.ProjectsDataManager;
import com.android.locusideas.home.projects.data.ProjectsDataSource;
import com.android.locusideas.home.projects.models.ProjectMedia;
import com.android.locusideas.home.projects.models.ProjectMediaResponse;

import java.util.List;

/**
 * Created on 06/07/16.
 */

public class ProjectPresenter extends BasePresenter<ProjectView> {

    private final ProjectsDataSource projectsDataSource;
    private final ProjectHolder projectHolder;

    public ProjectPresenter(ProjectsDataSource projectsDataSource, ProjectHolder projectHolder){
        this.projectsDataSource = projectsDataSource;
        this.projectHolder = projectHolder;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadProjectMedia();
        view.updateProjectView(projectHolder.getProject());
    }

    public void loadProjectMedia(){
        projectsDataSource.loadProjectMedias(projectHolder.getProject().getId(), new LoadProjectMediaCallback(this));
    }

    public void onLoadMediaSuccess(List<ProjectMedia> projectMedias){
        view.showMedia(projectMedias);
    }

    public void onError(String message){
        view.showError(message);
    }

    private static class LoadProjectMediaCallback implements ApiCallback<ProjectMediaResponse> {

        ProjectPresenter projectPresenter;

        public LoadProjectMediaCallback(ProjectPresenter projectPresenter) {
            this.projectPresenter = projectPresenter;
        }

        @Override
        public void onSuccess(ProjectMediaResponse result) {
            if(projectPresenter.isViewActive()) {
                projectPresenter.onLoadMediaSuccess(result.getProjectMedias());
            }
            projectPresenter = null;
        }

        @Override
        public void onFailure(ApiError error) {
            if(projectPresenter.isViewActive()) {
                projectPresenter.onError(error.getMessage());
            }
            projectPresenter = null;
        }
    }

}
