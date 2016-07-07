package com.android.locusideas.core.utils.mvp;

/**
 * Created on 04/07/16.
 */
public abstract class BasePresenter<V>{

    protected V view;

    final public void bindView(V view){
        this.view = view;
    }

    final public void unBindView(){
        this.view = null;
    }

    final public boolean isViewActive(){
        if (view != null){
            return true;
        }
        return false;
    }

    public void onCreate(){

    }

    public void onResume(){

    }

}
