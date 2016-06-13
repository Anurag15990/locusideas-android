package com.android.locusideas.core.data.auth.injection;
import com.android.locusideas.LocusApplication;
import com.android.locusideas.core.data.auth.AuthDataContract;
import com.android.locusideas.core.data.auth.AuthDataManager;
import com.android.locusideas.core.data.auth.local.AuthLocalDataService;
import com.android.locusideas.core.data.auth.remote.AuthRemoteDataService;
import com.android.locusideas.core.utils.injection.PerComponent;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 28/05/16.
 */
@Module
public class AuthModule {

    @Provides
    @PerComponent
    public AuthDataContract.Services providesAuthDataManager(AuthRemoteDataService authRemoteDataService, AuthLocalDataService authLocalDataService){
        return new AuthDataManager(authRemoteDataService, authLocalDataService);
    }

    @Provides
    @PerComponent
    public AuthRemoteDataService providesAuthRemoteDataService(){
        return new AuthRemoteDataService();
    }

    @Provides
    @PerComponent
    public AuthLocalDataService providesAuthLocalDataService(LocusApplication application){
        return new AuthLocalDataService(application);
    }
}
