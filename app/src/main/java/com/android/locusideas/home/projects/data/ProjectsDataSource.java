package com.android.locusideas.home.projects.data;

import com.android.locusideas.home.projects.ProjectsContract;

/**
 * Created on 28/06/16.
 */

public interface ProjectsDataSource {

    void setProjectsPresenter(ProjectsContract.Presenter projectsPresenter);

    void likeProjectWithId(String projectId);

    void loadProjects();


}
