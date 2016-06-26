package com.android.locusideas.home;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import com.android.locusideas.home.injection.DaggerMainShellActivityComponent;
import com.android.locusideas.home.injection.MainShellActivityModule;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.locusideas.locusideas.R;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by satyaiyengar on 18/06/16.
 */

public class MainShellActivity extends AppCompatActivity{

    @BindView(R.id.main_shell_view_pager)
    AHBottomNavigationViewPager mainShellBottomNavigationViewPager;

    @BindView(R.id.main_shell_bottom_navigation)
    AHBottomNavigation mainShellBottomNavigation;

    MainShellViewPagerAdapter mainShellViewPagerAdapter;

    @Inject
    MainShellFragmentsProvider mainShellFragmentsProvider;

    private OnTabSelectedListener onTabSelectedListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shell);
        ButterKnife.bind(this);

        DaggerMainShellActivityComponent.builder()
                .mainShellActivityModule(new MainShellActivityModule())
                .build()
                .inject(this);

        initUi();
    }

    private void initUi(){
        mainShellViewPagerAdapter = new MainShellViewPagerAdapter(getSupportFragmentManager(), mainShellFragmentsProvider);
        mainShellBottomNavigationViewPager.setOffscreenPageLimit(3);
        mainShellBottomNavigationViewPager.setAdapter(mainShellViewPagerAdapter);
        initBottomNavigation();
    }

    private void initBottomNavigation(){
        mainShellBottomNavigation.addItems(mainShellFragmentsProvider.getFragmentTabIcons());
        mainShellBottomNavigation.setAccentColor(ResourcesCompat.getColor(getResources(), R.color.colorAccent,null));

        ColorDrawable inActiveColorDrawable = new ColorDrawable(ResourcesCompat.getColor(getResources(),android.R.color.black,null));
        inActiveColorDrawable.setAlpha(34);
        mainShellBottomNavigation.setInactiveColor(inActiveColorDrawable.getColor());

        onTabSelectedListener = new OnTabSelectedListener(this);
        mainShellBottomNavigation.setOnTabSelectedListener(onTabSelectedListener);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onTabSelectedListener.onDestroy();
        mainShellBottomNavigation.setOnTabSelectedListener(null);
    }

    private static class OnTabSelectedListener implements AHBottomNavigation.OnTabSelectedListener {

        MainShellActivity mainShellActivity;

        public OnTabSelectedListener(MainShellActivity mainShellActivity){
            this.mainShellActivity = mainShellActivity;
        }

        @Override
        public boolean onTabSelected(int position, boolean wasSelected) {
            if (wasSelected){
                //Selecting already selected tab
                //Refresh fragment
                mainShellActivity.mainShellViewPagerAdapter.getItem(position).refresh();
                return true;
            }

            mainShellActivity.mainShellBottomNavigationViewPager.setCurrentItem(position, false);
            return true;
        }

        public void onDestroy(){
            mainShellActivity = null;
        }
    }

}
