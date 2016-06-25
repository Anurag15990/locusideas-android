package com.android.locusideas.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.locusideas.locusideas.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by satyaiyengar on 18/06/16.
 */

public class MainShellActivity extends AppCompatActivity{

    @BindView(R.id.main_shell_view_pager)
    AHBottomNavigationViewPager ahBottomNavigationViewPager;

    MainShellViewPagerAdapter mainShellViewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shell);
        ButterKnife.bind(this);
    }


}
