package com.android.locusideas.home.designers.designerProfile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.locusideas.home.designers.DesignersView;
import com.locusideas.locusideas.R;

/**
 * Created on 24/07/16.
 */

public class DesignerProfileActivity extends AppCompatActivity implements DesignersView{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer_profile);
    }

}
