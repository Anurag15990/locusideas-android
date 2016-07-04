package com.android.locusideas.core.utils.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.util.SparseArray;

/**
 * Created on 04/07/16.
 */

//TODO expire presenters
public class PresenterManager {
    private static final String KEY_PRESENTER_ID = "key_presenter_id";

    private final SparseArray<BasePresenter> presenters;

    //TODO create a id pool
    private int currentId;

    public PresenterManager() {
        this.presenters = new SparseArray<>();
    }

    @UiThread
    public <P extends BasePresenter> P restorePresenter(@NonNull Bundle savedState){
        int presenterId = savedState.getInt(KEY_PRESENTER_ID);
        P presenter = (P)presenters.get(presenterId);
        presenters.remove(presenterId);
        return presenter;
    }

    @UiThread
    public void savePresenter(@NonNull BasePresenter basePresenter, Bundle outState){
        int presenterId = currentId++;
        presenters.put(presenterId, basePresenter);
        outState.putInt(KEY_PRESENTER_ID, presenterId);
    }

}
