package com.android.locusideas.home.designers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.locusideas.core.ui.widgets.TextViewPlus;
import com.android.locusideas.home.BaseHomeFragment;
import com.locusideas.locusideas.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 25/06/16.
 */
public class DesignersFragment extends BaseHomeFragment {

    //TODO added for testing remove
    @BindView(R.id.fragment_name)
    TextViewPlus fragmentName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_designers, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateFragmentName();
    }

    //TODO added for testing remove
    private void updateFragmentName(){
        fragmentName.setText(this.getClass().getSimpleName());
    }


    @Override
    public void refresh() {

    }
}