package com.android.locusideas.home.design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.locusideas.core.ui.TabIcon;
import com.android.locusideas.home.BaseHomeFragment;
import com.locusideas.locusideas.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 25/06/16.
 */

public class DesignsFragment extends BaseHomeFragment{

    @BindView(R.id.designs_recycler_view)
    RecyclerView designsRecyclerView;

    public static DesignsFragment getInstance(){
        return new DesignsFragment();
    }

    public static TabIcon getTabIcon(){
        return new TabIcon(R.string.designs_str, R.drawable.ic_trending_black_24dp, android.R.color.holo_blue_light);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_designs, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void refresh() {

    }
}
