package com.android.locusideas.home.projects.di;

import com.android.locusideas.core.utils.SharedPreferencesManager;
import com.android.locusideas.core.utils.injection.PerActivity;
import com.android.locusideas.home.projects.ProjectsPresenter;
import com.android.locusideas.home.projects.ProjectsView;
import com.android.locusideas.home.projects.data.ProjectsDataManager;
import com.android.locusideas.home.projects.data.ProjectsDataSource;
import com.android.locusideas.home.projects.data.remote.ProjectsRemoteDataService;
import dagger.Module;
import dagger.Provides;

/**
 * Created on 30/06/16.
 */
@Module
public class ProjectsModule{

    private ProjectsView view;

    public ProjectsModule(ProjectsView view){
        this.view = view;
    }

    @Provides
    @PerActivity
    public ProjectsPresenter providesPresenter(ProjectsDataSource projectsDataSource){
        return new ProjectsPresenter(view, projectsDataSource);
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
