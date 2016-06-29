package com.android.locusideas.core.utils;

import com.android.locusideas.core.data.models.ApiError;

/**
 * Created on 28/06/16.
 */

public interface ApiCallback<T> {
    void onSuccess(T result);
    void onFailure(ApiError error);
}
