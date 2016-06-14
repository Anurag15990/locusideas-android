package com.android.locusideas.core;

/**
 * Created by satyaiyengar on 14/06/16.
 */

public class BaseModule <T> {
    private T mView;

    public BaseModule(T view){
        mView = view;
    }

    public T getView(){
        return mView;
    }
}
