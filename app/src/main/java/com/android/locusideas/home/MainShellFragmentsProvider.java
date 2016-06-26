package com.android.locusideas.home;

import com.android.locusideas.home.design.DesignsFragment;
import com.android.locusideas.home.designers.DesignersFragment;
import com.android.locusideas.home.requirements.RequirementsFragment;
import com.android.locusideas.home.settings.SettingsFragment;

/**
 * Created on 25/06/16.
 */

public class MainShellFragmentsProvider {

    public DesignsFragment getDesignsFragment(){
        return DesignsFragment.getInstance();
    }

    public DesignersFragment getDesignersFragment(){
        return DesignersFragment.getInstance();
    }

    public RequirementsFragment getRequirementsFragment(){
        return RequirementsFragment.getInstance();
    }

    public SettingsFragment getSettingsFragment(){
        return SettingsFragment.getInstance();
    }

}
