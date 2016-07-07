package com.android.locusideas.home.projects.data;

import com.android.locusideas.core.utils.ApiCallback;
import com.android.locusideas.home.projects.ProjectsPresenter;
import com.android.locusideas.home.projects.models.ProjectMediaResponse;

/**
 * Created on 28/06/16.
 */

public interface ProjectsDataSource {

    void setProjectsPresenter(ProjectsPresenter projectsPresenter);

    void likeProjectWithId(String projectId);

    void loadProjects();

    void loadProjectMedias(String projectId, ApiCallback<ProjectMediaResponse> projectMediaResponseApiCallback);
}
