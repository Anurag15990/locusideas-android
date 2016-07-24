package com.android.locusideas.home.designers.injection;

import com.android.locusideas.core.utils.injection.PerActivity;
import com.android.locusideas.home.designers.DesignersPresenter;
import com.android.locusideas.home.designers.DesignersView;

import dagger.Component;

/**
 * Created on 06/07/16.
 */
@PerActivity
@Component(modules = {DesignersModule.class})
public interface DesignersComponent {
    DesignersPresenter getPresenter();
    void inject(DesignersView designersView);
}
