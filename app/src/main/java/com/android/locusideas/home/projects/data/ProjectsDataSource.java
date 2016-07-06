package com.android.locusideas.home.projects.data;

import com.android.locusideas.home.projects.ProjectsPresenter;

/**
 * Created on 28/06/16.
 */

public interface ProjectsDataSource {

    void setProjectsPresenter(ProjectsPresenter projectsPresenter);

    void likeProjectWithId(String projectId);

    void loadProjects();

}
