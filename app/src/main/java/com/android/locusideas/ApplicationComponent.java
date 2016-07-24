package com.android.locusideas;

import com.android.locusideas.core.utils.SharedPreferencesManager;
import com.android.locusideas.home.projects.project.ProjectHolder;
import javax.inject.Singleton;
import dagger.Component;

/**
 * Created on 26/05/16.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    LocusApplication locusApplication();
    SharedPreferencesManager sharedPreferenceManager();
    ProjectHolder getProjectHolder();

    void inject(LocusApplication la);
}
