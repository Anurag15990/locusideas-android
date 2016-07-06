package com.android.locusideas.core.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.android.locusideas.LocusApplication;
import com.android.locusideas.core.utils.mvp.BasePresenter;
import com.android.locusideas.core.utils.mvp.PresenterManager;
/**
 * Created on 04/07/16.
 */

public abstract class BaseActivity<V, P extends BasePresenter<V>> extends AppCompatActivity{

    private PresenterManager presenterManager;
    protected P presenter;
    protected V view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenterManager = ((LocusApplication)getApplicationContext()).getPresenterManager();
        if(savedInstanceState != null) {
            presenter = presenterManager.restorePresenter(savedInstanceState);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.bindView(getView());
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unBindView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterManager = null;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenterManager.savePresenter(presenter, outState);
    }

    protected abstract V getView();

}
