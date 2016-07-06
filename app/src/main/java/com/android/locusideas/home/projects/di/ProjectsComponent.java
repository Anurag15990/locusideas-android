package com.android.locusideas.home.projects.di;

import com.android.locusideas.ApplicationComponent;
import com.android.locusideas.core.utils.injection.PerActivity;
import com.android.locusideas.home.projects.ProjectsFragment;
import com.android.locusideas.home.projects.ProjectsPresenter;

import dagger.Component;

/**
 * Created on 30/06/16.
 */
@PerActivity
@Component(dependencies = {ApplicationComponent.class}, modules = {ProjectsModule.class})
public interface ProjectsComponent {

    ProjectsPresenter getProjectsPresenter();

    void inject(ProjectsFragment projectsFragment);
}
