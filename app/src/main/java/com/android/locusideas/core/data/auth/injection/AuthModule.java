package com.android.locusideas.core.data.auth.injection;
import com.android.locusideas.ApplicationModule;
import com.android.locusideas.LocusApplication;
import com.android.locusideas.core.data.auth.AuthDataContract;
import com.android.locusideas.core.data.auth.AuthDataManager;
import com.android.locusideas.core.data.auth.local.AuthLocalDataService;
import com.android.locusideas.core.data.auth.remote.AuthRemoteDataService;
import com.android.locusideas.core.utils.injection.PerActivity;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created on 28/05/16.
 */
@Module(includes = {ApplicationModule.class})
public class AuthModule {

    @Provides
    @Singleton
    public AuthDataContract.Services providesAuthDataManager(AuthRemoteDataService authRemoteDataService, AuthLocalDataService authLocalDataService){
        return new AuthDataManager(authRemoteDataService, authLocalDataService);
    }

    @Provides
    @Singleton
    public AuthRemoteDataService providesAuthRemoteDataService(){
        return new AuthRemoteDataService();
    }

    @Provides
    @Singleton
    public AuthLocalDataService providesAuthLocalDataService(LocusApplication application){
        return new AuthLocalDataService(application);
    }
}
