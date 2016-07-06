package com.android.locusideas.home.projects;

import com.android.locusideas.core.utils.mvp.BasePresenter;
import com.android.locusideas.home.projects.data.ProjectsDataSource;
import com.android.locusideas.home.projects.models.Project;
import java.util.List;

/**
 * Created on 28/06/16.
 */

public class ProjectsPresenter extends BasePresenter<ProjectsView>{

    ProjectsView view;

    ProjectsDataSource projectsDataSource;

    public ProjectsPresenter(ProjectsView view, ProjectsDataSource projectsDataSource){
        this.view = view;
        this.projectsDataSource = projectsDataSource;
        this.projectsDataSource.setProjectsPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void loadProjects(){
        projectsDataSource.loadProjects();
    }

    public void onProjectsLoaded(List<Project> projects){
        view.initializeAdapterWithProjects(projects);
    }

    public void showError(String errorMessage){

    }

}
