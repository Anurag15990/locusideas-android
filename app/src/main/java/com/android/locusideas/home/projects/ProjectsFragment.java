package com.android.locusideas.home.projects;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.locusideas.LocusApplication;
import com.android.locusideas.home.BaseHomeFragment;
import com.android.locusideas.home.designers.designerProfile.DesignerProfileActivity;
import com.android.locusideas.home.projects.di.DaggerProjectsComponent;
import com.android.locusideas.home.projects.di.ProjectsComponent;
import com.android.locusideas.home.projects.di.ProjectsModule;
import com.android.locusideas.home.projects.models.Project;
import com.android.locusideas.home.projects.project.ProjectActivity;
import com.android.locusideas.home.projects.project.ProjectHolder;
import com.locusideas.locusideas.R;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 25/06/16.
 */
public class ProjectsFragment extends BaseHomeFragment<ProjectsView, ProjectsPresenter> implements ProjectsView{

    @BindView(R.id.projects_recycler_view)
    RecyclerView projectsRecyclerView;

    ProjectsAdapter projectsAdapter;
    ProjectsComponent projectsComponent;
    LocusApplication locusApplication;
    ProjectHolder projectHolder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locusApplication = ((LocusApplication)getActivity().getApplicationContext());

        projectsComponent  = DaggerProjectsComponent.builder()
                .applicationComponent(locusApplication.getApplicationComponent())
                .projectsModule(new ProjectsModule(this))
                .build();

        if(presenter == null){
            presenter = projectsComponent.getProjectsPresenter();
        }

        projectHolder = locusApplication.getProjectHolder();
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

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        projectsAdapter = new ProjectsAdapter(getActivity());
        projectsAdapter.setProjectsAdapterCallbacks(new ProjectsAdapterCallbacksImpl(getActivity(),projectsAdapter, projectHolder));
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
    public void onDestroy() {
        super.onDestroy();
        projectsAdapter.onDestroy();
        projectsAdapter = null;
        projectsComponent = null;
    }

    @Override
    protected ProjectsView getMVPView() {
        return this;
    }

    @Override
    public void refresh() {
        presenter.loadProjects();
    }

    private static class ProjectsAdapterCallbacksImpl implements ProjectsAdapter.ProjectsAdapterCallbacks{
        ProjectsAdapter projectsAdapter;
        ProjectHolder projectHolder;
        Context appContext;

        public ProjectsAdapterCallbacksImpl(Context appContext,ProjectsAdapter projectsAdapter, ProjectHolder projectHolder){
            this.projectsAdapter = projectsAdapter;
            this.projectHolder = projectHolder;
            this.appContext = appContext;
        }

        @Override
        public void onAdapterEmpty() {

        }

        @Override
        public void onItemClick(int itemPosition) {
            projectHolder.setProject(projectsAdapter.getItemAt(itemPosition));
            Intent projectIntent = new Intent(appContext, DesignerProfileActivity.class);
            appContext.startActivity(projectIntent);
        }

        @Override
        public void onActionLikeClick(int itemPosition) {

        }

        @Override
        public void onDestroy() {
            appContext = null;
            projectHolder = null;
            projectsAdapter = null;
        }

    }

}
