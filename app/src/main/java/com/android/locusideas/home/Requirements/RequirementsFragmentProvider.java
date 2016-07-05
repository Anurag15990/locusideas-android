package com.android.locusideas.home.requirements;

import com.android.locusideas.core.ui.TabIcon;
import com.android.locusideas.home.BaseHomeFragment;
import com.android.locusideas.home.FragmentProvider;
import com.locusideas.locusideas.R;

/**
 * Created on 05/07/16.
 */

public class RequirementsFragmentProvider implements FragmentProvider<BaseHomeFragment> {

    @Override
    public BaseHomeFragment getNewInstance() {
        return new RequirementsFragment();
    }

    @Override
    public TabIcon getIcon() {
        return new TabIcon(R.string.requirements_tab_title, R.drawable.ic_edit_requirements_black_24dp, android.R.color.holo_blue_light);
    }

}
