package com.android.locusideas.home;

import com.android.locusideas.core.ui.TabIcon;

/**
 * Created on 05/07/16.
 */

public interface FragmentProvider<F> {

    F getNewInstance();

    TabIcon getIcon();

}
