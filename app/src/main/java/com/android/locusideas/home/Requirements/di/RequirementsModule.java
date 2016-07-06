package com.android.locusideas.home.requirements.di;

import com.android.locusideas.core.utils.injection.PerActivity;
import com.android.locusideas.home.requirements.RequirementsPresenter;
import com.android.locusideas.home.requirements.RequirementsView;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 06/07/16.
 */
@Module
public class RequirementsModule {
    RequirementsView requirementsView;

    public RequirementsModule(RequirementsView requirementsView){
        this.requirementsView = requirementsView;
    }

    @Provides
    @PerActivity
    public RequirementsPresenter provideRequirementsPresenter(){
        return new RequirementsPresenter();
    }

}
