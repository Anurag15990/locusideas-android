package com.android.locusideas.home;

import com.android.locusideas.core.ui.BaseFragment;
import com.android.locusideas.core.utils.mvp.BasePresenter;

/**
 * Created on 25/06/16.
 */

public abstract class BaseHomeFragment<V, P extends BasePresenter<V>> extends BaseFragment<V,P>{
    public abstract void refresh();
}
