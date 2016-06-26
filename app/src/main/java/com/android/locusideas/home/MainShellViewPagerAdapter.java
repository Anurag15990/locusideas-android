package com.android.locusideas.home;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created on 18/06/16.
 */
public class MainShellViewPagerAdapter extends FragmentPagerAdapter {

    private MainShellFragmentsProvider mainShellFragmentsProvider;

    public MainShellViewPagerAdapter(FragmentManager fm, MainShellFragmentsProvider mainShellFragmentsProvider) {
        super(fm);
        this.mainShellFragmentsProvider = mainShellFragmentsProvider;
    }

    @Override
    public BaseHomeFragment getItem(int position) {
        return mainShellFragmentsProvider.provideFragmentAtPosition(position);
    }

    @Override
    public int getCount() {
        return mainShellFragmentsProvider.getFragmentCount();
    }

}
