package com.android.locusideas.home.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.locusideas.core.ui.widgets.TextViewPlus;
import com.android.locusideas.home.BaseHomeFragment;
import com.android.locusideas.home.settings.di.DaggerSettingsComponent;
import com.android.locusideas.home.settings.di.SettingsComponent;
import com.android.locusideas.home.settings.di.SettingsModule;
import com.locusideas.locusideas.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 25/06/16.
 */
public class SettingsFragment extends BaseHomeFragment<SettingsView, SettingsPresenter> implements SettingsView{

    //TODO added for testing remove
    @BindView(R.id.fragment_name)
    TextViewPlus fragmentName;

    SettingsComponent settingsComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        settingsComponent = DaggerSettingsComponent.builder()
                .settingsModule(new SettingsModule(this))
                .build();

        if(presenter == null){
            presenter = settingsComponent.getPresenter();
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateFragmentName();
    }

    @Override
    protected SettingsView getMVPView() {
        return this;
    }

    //TODO added for testing remove
    private void updateFragmentName(){
        fragmentName.setText(this.getClass().getSimpleName());
    }

    @Override
    public void refresh() {

    }
}
