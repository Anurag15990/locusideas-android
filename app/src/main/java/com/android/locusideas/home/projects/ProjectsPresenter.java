package com.android.locusideas.home.projects;

import com.android.locusideas.home.projects.data.ProjectsDataSource;
import com.android.locusideas.home.projects.models.Project;
import java.util.List;

/**
 * Created on 28/06/16.
 */

public class ProjectsPresenter implements ProjectsContract.Presenter{

    ProjectsContract.View view;

    ProjectsDataSource projectsDataSource;

    public ProjectsPresenter(ProjectsContract.View view, ProjectsDataSource projectsDataSource){
        this.view = view;
        this.projectsDataSource = projectsDataSource;
        this.projectsDataSource.setProjectsPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadProjects(){
        projectsDataSource.loadProjects();
    }

    @Override
    public void onProjectsLoaded(List<Project> projects){
        view.initializeAdapterWithProjects(projects);
    }

    @Override
    public void showError(String errorMessage){

    }

}
