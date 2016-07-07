package com.android.locusideas.home.projects.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;
import com.android.locusideas.LocusApplication;
import com.android.locusideas.core.ui.BaseActivity;
import com.android.locusideas.home.projects.models.Project;
import com.android.locusideas.home.projects.project.di.DaggerProjectComponent;
import com.android.locusideas.home.projects.project.di.ProjectComponent;
import com.android.locusideas.home.projects.project.di.ProjectModule;
import com.locusideas.locusideas.R;
import butterknife.ButterKnife;

/**
 * Created on 06/07/16.
 */

public class ProjectActivity extends BaseActivity<ProjectView, ProjectPresenter> implements ProjectView {

    ProjectComponent projectComponent;
    ProjectHolder projectHolder;
    Project project;
    LocusApplication locusApplication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        ButterKnife.bind(this);

        projectComponent = DaggerProjectComponent.builder()
                .projectModule(new ProjectModule(this))
                .build();

        if(presenter == null){
            presenter = projectComponent.getPresenter();
        }

        locusApplication = (LocusApplication)getApplication();

        projectHolder = locusApplication.getProjectHolder();

        if(!projectHolder.hasProject()){
            //TODO throw error
            Toast.makeText(this, "Project not found in holder", Toast.LENGTH_SHORT).show();
            finish();
        }

        project = projectHolder.getProject();
    }

    @Override
    protected ProjectView getView() {
        return this;
    }

}
