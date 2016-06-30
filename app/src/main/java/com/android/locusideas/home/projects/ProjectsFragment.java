package com.android.locusideas.home.projects;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.locusideas.LocusApplication;
import com.android.locusideas.core.ui.TabIcon;
import com.android.locusideas.home.BaseHomeFragment;
import com.android.locusideas.home.projects.injection.DaggerProjectsComponent;
import com.android.locusideas.home.projects.injection.ProjectsModule;
import com.android.locusideas.home.projects.models.Project;
import com.locusideas.locusideas.R;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 25/06/16.
 */

public class ProjectsFragment extends BaseHomeFragment implements ProjectsContract.View{

    @BindView(R.id.projects_recycler_view)
    RecyclerView projectsRecyclerView;

    ProjectsAdapter projectsAdapter;

    @Inject
    ProjectsContract.Presenter presenter;

    static ProjectsFragment instance;

    public static ProjectsFragment getInstance(){
        if (instance == null){
            instance = new ProjectsFragment();
        }
        return instance;
    }

    public static TabIcon getTabIcon(){
        return new TabIcon(R.string.projects_tab_title, R.drawable.ic_trending_black_24dp, android.R.color.holo_blue_light);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_projects, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DaggerProjectsComponent.builder()
                .applicationComponent(((LocusApplication)getActivity().getApplicationContext()).getApplicationComponent())
                .projectsModule(new ProjectsModule(this))
                .build()
                .inject(this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        projectsAdapter = new ProjectsAdapter(getActivity(), new ProjectsAdapterCallbacksImpl());
        projectsRecyclerView.setLayoutManager(mLayoutManager);
        projectsRecyclerView.setAdapter(projectsAdapter);
        presenter.loadProjects();
    }

    @Override
    public void initializeAdapterWithProjects(List<Project> projects){
        projectsAdapter.initializeWithItems(projects);
    }

    @Override
    public void addProjectsAtFront(List<Project> projects){
        projectsAdapter.insertItems(projects, 0);
    }

    @Override
    public void addProjectsAtRear(List<Project> projects){
        projectsAdapter.insertItems(projects, projectsAdapter.getItemCount());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void refresh() {
        presenter.loadProjects();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    private static class ProjectsAdapterCallbacksImpl implements ProjectsAdapter.ProjectsAdapterCallbacks{

        @Override
        public void onAdapterEmpty() {

        }

        @Override
        public void onItemClick(int itemPosition) {

        }

        @Override
        public void onActionLikeClick(int itemPosition) {

        }

    }

}
