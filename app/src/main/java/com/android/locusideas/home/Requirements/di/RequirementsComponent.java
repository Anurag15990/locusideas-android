package com.android.locusideas.home.requirements.di;

import com.android.locusideas.core.utils.injection.PerActivity;
import com.android.locusideas.home.requirements.RequirementsPresenter;
import com.android.locusideas.home.requirements.RequirementsView;
import dagger.Component;

/**
 * Created on 06/07/16.
 */
@PerActivity
@Component(modules = {RequirementsModule.class})
public interface RequirementsComponent {
    RequirementsPresenter getRequirementsPresenter();
    void inject(RequirementsView requirementsView);
}
