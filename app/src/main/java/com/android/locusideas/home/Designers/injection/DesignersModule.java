package com.android.locusideas.home.designers.injection;

import com.android.locusideas.core.utils.injection.PerActivity;
import com.android.locusideas.home.designers.DesignersPresenter;
import com.android.locusideas.home.designers.DesignersView;
import dagger.Module;
import dagger.Provides;

/**
 * Created on 06/07/16.
 */
@Module
public class DesignersModule {

    DesignersView designersView;

    public DesignersModule(DesignersView designersView){
        this.designersView = designersView;
    }

    @Provides
    @PerActivity
    public DesignersPresenter providesPresenter(){
        return new DesignersPresenter();
    }

}
