package com.android.locusideas.home;

import com.android.locusideas.core.ui.TabIcon;
import com.android.locusideas.home.projects.ProjectsFragment;
import com.android.locusideas.home.designers.DesignersFragment;
import com.android.locusideas.home.requirements.RequirementsFragment;
import com.android.locusideas.home.settings.SettingsFragment;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 25/06/16.
 */

public class MainShellFragmentsProvider {

    public BaseHomeFragment provideFragmentAtPosition(int position){
        BaseHomeFragment item = null;

        if (position == 0){
            item = provideDesignsFragment();
        } else if (position == 1){
            item = provideDesignersFragment();
        } else if (position == 2){
            item = provideRequirementsFragment();
        } else if (position == 3){
            item = provideSettingsFragment();
        }

        return item;
    }

    private ProjectsFragment provideDesignsFragment(){
        return ProjectsFragment.getInstance();
    }

    private DesignersFragment provideDesignersFragment(){
        return DesignersFragment.getInstance();
    }

    private RequirementsFragment provideRequirementsFragment(){
        return RequirementsFragment.getInstance();
    }

    private SettingsFragment provideSettingsFragment(){
        return SettingsFragment.getInstance();
    }

    //TODO find a way to get icons from respective fragments
    public List<AHBottomNavigationItem> getFragmentTabIcons(){
        List<AHBottomNavigationItem> items = new ArrayList<>();

        for(int i = 0; i < getFragmentCount(); i++){
            TabIcon icon = null;

            if (i == 0){
                icon = ProjectsFragment.getTabIcon();
            } else if (i == 1){
                icon = DesignersFragment.getTabIcon();
            } else if (i == 2){
                icon = RequirementsFragment.getTabIcon();
            } else if (i == 3){
                icon = SettingsFragment.getTabIcon();
            }

            items.add(new AHBottomNavigationItem(icon.getTitleStringRes(), icon.getIconRes(), icon.getColorRes()));
        }

        return items;
    }

    public int getFragmentCount(){
        return 4;
    }

}
