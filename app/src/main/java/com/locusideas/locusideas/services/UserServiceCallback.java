package com.locusideas.locusideas.services;

import android.support.annotation.NonNull;

/**
 * Created by anurag on 5/14/16.
 */

public interface UserServiceCallback<T> {
    void onSuccess(@NonNull T response);
    void onFailure(@NonNull String errorMessage);
}
