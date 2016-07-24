package com.android.locusideas.core;

import com.android.locusideas.core.utils.mvp.BasePresenter;

/**
 * Created on 06/07/16.
 */

public interface BaseMVPComponent<V,P extends BasePresenter<V>> {
    P getPresenter();
}
