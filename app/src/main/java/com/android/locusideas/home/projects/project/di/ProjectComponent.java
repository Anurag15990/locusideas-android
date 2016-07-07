package com.android.locusideas.home.projects.project.di;

import com.android.locusideas.core.BaseMVPComponent;
import com.android.locusideas.core.utils.injection.PerComponent;
import dagger.Component;
import com.android.locusideas.home.projects.project.ProjectPresenter;
import com.android.locusideas.home.projects.project.ProjectView;

/**
 * Created on 06/07/16.
 */
@PerComponent
@Component(modules = {ProjectModule.class})
public interface ProjectComponent extends BaseMVPComponent<ProjectView,ProjectPresenter>{
    void inject(ProjectView projectView);
}
