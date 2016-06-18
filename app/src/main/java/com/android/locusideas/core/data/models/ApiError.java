package com.android.locusideas.core.data.models;

/**
 * Created on 28/05/16.
 */
public class ApiError{
    private final String mMessage;

    public ApiError(String message){
        mMessage = message;
    }

    public String getMessage(){
        return mMessage;
    }
}
