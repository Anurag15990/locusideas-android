package com.android.locusideas.core.data.auth.injection;

import com.android.locusideas.ApplicationComponent;
import com.android.locusideas.core.data.auth.AuthDataContract;
import com.android.locusideas.core.utils.injection.PerComponent;

import dagger.Component;

/**
 * Created on 28/05/16.
 */
@PerComponent
@Component(dependencies = {ApplicationComponent.class}, modules = {AuthModule.class})
public interface AuthComponent{
    AuthDataContract.Services getAuthServices();
}
