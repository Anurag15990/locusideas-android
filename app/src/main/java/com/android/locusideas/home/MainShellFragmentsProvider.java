package com.android.locusideas.home;

import com.android.locusideas.core.ui.TabIcon;
import com.android.locusideas.home.designers.DesignersFragmentProvider;
import com.android.locusideas.home.projects.ProjectsFragmentProvider;
import com.android.locusideas.home.requirements.RequirementsFragmentProvider;
import com.android.locusideas.home.settings.SettingsFragmentProvider;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 25/06/16.
 */

//TODO implement way to destroy fragments
public class MainShellFragmentsProvider {

    List<FragmentProvider<BaseHomeFragment>> fragmentProviders;
    List<BaseHomeFragment> fragments = new ArrayList<>();

    public MainShellFragmentsProvider(){
        fragmentProviders = new ArrayList<>();
        fragments = new ArrayList<>();

        fragmentProviders.add(new ProjectsFragmentProvider());
        fragmentProviders.add(new DesignersFragmentProvider());
        fragmentProviders.add(new RequirementsFragmentProvider());
        fragmentProviders.add(new SettingsFragmentProvider());
    }

    public BaseHomeFragment provideFragmentAtPosition(int position){
        BaseHomeFragment item = null;

        if(fragments.size() > position){
            //fragment already created and stored in list
            //return existing fragment
            item = fragments.get(position);
        } else {
            //create new fragment instance
            item = fragmentProviders.get(position).getNewInstance();
            fragments.add(item);
        }

        return item;
    }

    public List<AHBottomNavigationItem> getFragmentTabIcons(){
        List<AHBottomNavigationItem> items = new ArrayList<>();

        TabIcon icon;

        for(int i = 0; i < getFragmentCount(); i++){
            icon = fragmentProviders.get(i).getIcon();
            items.add(new AHBottomNavigationItem(icon.getTitleStringRes(), icon.getIconRes(), icon.getColorRes()));
        }

        return items;
    }

    public int getFragmentCount(){
        return fragmentProviders.size();
    }

}
