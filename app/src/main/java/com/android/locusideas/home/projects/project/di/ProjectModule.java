package com.android.locusideas.home.projects.project.di;

import com.android.locusideas.core.utils.injection.PerComponent;
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
    @PerComponent
    public ProjectPresenter providesProjectPresenter(){
        return new ProjectPresenter();
    }

}
