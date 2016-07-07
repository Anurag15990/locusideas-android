package com.android.locusideas.home.projects.project.di;

import com.android.locusideas.core.utils.SharedPreferencesManager;
import com.android.locusideas.core.utils.injection.PerActivity;
import com.android.locusideas.core.utils.injection.PerComponent;
import com.android.locusideas.home.projects.data.ProjectsDataManager;
import com.android.locusideas.home.projects.data.ProjectsDataSource;
import com.android.locusideas.home.projects.data.remote.ProjectsRemoteDataService;
import com.android.locusideas.home.projects.di.ProjectsModule;
import com.android.locusideas.home.projects.project.ProjectHolder;
import com.android.locusideas.home.projects.project.ProjectPresenter;
import com.android.locusideas.home.projects.project.ProjectView;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 06/07/16.
 */
@Module
public class ProjectModule {

    ProjectView projectView;

    public ProjectModule(ProjectView projectView){
        this.projectView = projectView;
    }

    @Provides
    @PerActivity
    public ProjectPresenter providesProjectPresenter(ProjectsDataSource projectsDataSource, ProjectHolder projectHolder){
        return new ProjectPresenter(projectsDataSource, projectHolder);
    }

    @Provides
    @PerActivity
    public ProjectsDataSource providesProjectsDataSource(ProjectsRemoteDataService projectsRemoteDataService){
        return new ProjectsDataManager(projectsRemoteDataService);
    }

    @Provides
    @PerActivity
    public ProjectsRemoteDataService providesProjectsRemoteDataService(SharedPreferencesManager sharedPreferencesManager){
        return new ProjectsRemoteDataService(sharedPreferencesManager);
    }

}
