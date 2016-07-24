package com.android.locusideas.home.requirements;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.locusideas.core.ui.widgets.TextViewPlus;
import com.android.locusideas.home.BaseHomeFragment;
import com.android.locusideas.home.requirements.di.DaggerRequirementsComponent;
import com.android.locusideas.home.requirements.di.RequirementsComponent;
import com.android.locusideas.home.requirements.di.RequirementsModule;
import com.locusideas.locusideas.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 25/06/16.
 */
public class RequirementsFragment extends BaseHomeFragment<RequirementsView, RequirementsPresenter> implements RequirementsView{

    //TODO added for testing remove
    @BindView(R.id.fragment_name)
    TextViewPlus fragmentName;

    RequirementsComponent requirementsComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requirementsComponent = DaggerRequirementsComponent.builder()
                .requirementsModule(new RequirementsModule(this))
                .build();

        if (presenter == null){
            presenter = requirementsComponent.getRequirementsPresenter();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_requirements, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateFragmentName();
    }

    @Override
    protected RequirementsView getMVPView() {
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
