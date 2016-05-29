package com.android.locusideas.core.data.auth.injection;

import com.android.locusideas.ApplicationComponent;
import com.android.locusideas.core.data.auth.AuthDataContract;
import com.android.locusideas.core.utils.injection.PerActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created on 28/05/16.
 */
@Singleton
@Component(modules = {AuthModule.class})
public interface AuthComponent{

}
