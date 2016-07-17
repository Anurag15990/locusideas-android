package com.android.locusideas.home.projects.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.locusideas.LocusApplication;
import com.android.locusideas.core.ui.BaseActivity;
import com.android.locusideas.core.utils.ActivityUtils;
import com.android.locusideas.home.projects.di.ProjectsComponent;
import com.android.locusideas.home.projects.models.Project;
import com.android.locusideas.home.projects.models.ProjectMedia;
import com.android.locusideas.home.projects.project.di.DaggerProjectComponent;
import com.android.locusideas.home.projects.project.di.ProjectComponent;
import com.android.locusideas.home.projects.project.di.ProjectModule;
import com.bumptech.glide.Glide;
import com.locusideas.locusideas.R;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 06/07/16.
 */

public class ProjectActivity extends BaseActivity<ProjectView, ProjectPresenter> implements ProjectView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.project_poster)
    ImageView projectPoster;

    @BindView(R.id.project_images)
    RecyclerView projectImagesView;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;

    @BindView(R.id.project_share)
    ImageView projectShareAction;

    @BindView(R.id.project_owner_avatar)
    ImageView projectOwnerAvatarCollapsed;

    ProjectComponent projectComponent;
    //TODO create component manager
    ProjectsComponent projectsComponent;
    LocusApplication locusApplication;
    ProjectMediaAdapter projectMediaAdapter;
    OnStateChangeListenerImpl onStateChangeListener;
    ActivityUtils.AppBarOffsetChangedListener appBarOffsetChangedListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        ButterKnife.bind(this);

        locusApplication = (LocusApplication)getApplication();

        projectComponent = DaggerProjectComponent.builder()
                .applicationComponent(locusApplication.getApplicationComponent())
                .projectModule(new ProjectModule(this))
                .build();

        if(presenter == null){
            presenter = projectComponent.getPresenter();
        }

        initializeToolbar();
        initializeMediaList();
        presenter.onCreate();
        onStateChangeListener = new OnStateChangeListenerImpl(presenter);
        appBarOffsetChangedListener = new ActivityUtils.AppBarOffsetChangedListener(onStateChangeListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
        appBarLayout.addOnOffsetChangedListener(appBarOffsetChangedListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        appBarLayout.removeOnOffsetChangedListener(appBarOffsetChangedListener);
    }

    private void initializeToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        collapsingToolbar.setExpandedTitleTypeface(FontUtils.getTypeface(getAssets(),
//                getResources().getString(R.string.font_montserrat_semibold)));

    }

    private void initializeMediaList(){
        projectMediaAdapter = new ProjectMediaAdapter();
        projectImagesView.setLayoutManager(new LinearLayoutManager(this));
        projectImagesView.setAdapter(projectMediaAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadProjectPoster(String imageUrl) {
        Glide.with(this).load(imageUrl).into(projectPoster);
    }

    @Override
    public void updateProjectView(Project project){
        collapsingToolbar.setTitle(project.getTitle());
        //collapsingToolbar.setTitleEnabled(false);
        Glide.with(this).load(project.getOwner().getPicture().getUrl()).dontAnimate().into(projectOwnerAvatarCollapsed);
        loadProjectPoster(project.getMedias().getInitial().get(0).getMedia().getUrl());
    }

    @Override
    protected ProjectView getView() {
        return this;
    }

    @Override
    public void showMedia(List<ProjectMedia> projectMedias) {
        projectMediaAdapter.addProjectMedia(projectMedias);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onToolbarCollapsed() {
//        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
//
//        projectOwnerAvatarCollapsed.startAnimation(fadeInAnimation);
//        projectShareAction.startAnimation(fadeInAnimation);

        projectOwnerAvatarCollapsed.setVisibility(View.VISIBLE);
        projectShareAction.setVisibility(View.VISIBLE);
    }

    @Override
    public void onToolbarExpanded() {
//        Animation fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
//
//        projectOwnerAvatarCollapsed.startAnimation(fadeOutAnimation);
//        projectShareAction.startAnimation(fadeOutAnimation);
        projectOwnerAvatarCollapsed.setVisibility(View.INVISIBLE);
        projectShareAction.setVisibility(View.INVISIBLE);
    }

    private static class OnStateChangeListenerImpl implements ActivityUtils.AppBarOffsetChangedListener.OnStateChangedListener{

        private ProjectPresenter projectPresenter;

        public OnStateChangeListenerImpl(ProjectPresenter projectPresenter){
            this.projectPresenter = projectPresenter;
        }

        @Override
        public void onStateChanged(int state) {
            projectPresenter.onAppBarStateChanged(state);
        }

    }


}
