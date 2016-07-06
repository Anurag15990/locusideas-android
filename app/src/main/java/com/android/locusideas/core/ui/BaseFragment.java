package com.android.locusideas.core.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import com.android.locusideas.LocusApplication;
import com.android.locusideas.core.utils.mvp.BasePresenter;
import com.android.locusideas.core.utils.mvp.PresenterManager;

/**
 * Created on 05/07/16.
 */
public abstract class BaseFragment<V, P extends BasePresenter<V>> extends Fragment{
    private PresenterManager presenterManager;
    protected P presenter;
    protected V view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenterManager = ((LocusApplication)getActivity().getApplicationContext()).getPresenterManager();
        if(savedInstanceState != null) {
            presenter = presenterManager.restorePresenter(savedInstanceState);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.bindView(getMVPView());
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unBindView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenterManager = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenterManager.savePresenter(presenter, outState);
    }

    protected abstract V getMVPView();
}
