package com.android.locusideas.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by satyaiyengar on 18/06/16.
 */

public class MainShellViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> mainPages = new ArrayList<>();

    public MainShellViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Adds fragments to list and calls notify adapter to update view
     *
     * @note - Call after setting adapter
     */
    public void init(){
    }

    @Override
    public Fragment getItem(int position) {
        return mainPages.get(position);
    }

    @Override
    public int getCount() {
        return mainPages.size();
    }

}
