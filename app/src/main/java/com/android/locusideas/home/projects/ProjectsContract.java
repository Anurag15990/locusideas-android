package com.android.locusideas.home.projects;

import com.android.locusideas.core.ui.BasePresenter;
import com.android.locusideas.core.ui.BaseView;
import com.android.locusideas.home.projects.models.Project;
import java.util.List;

/**
 * Created on 28/06/16.
 */

public interface ProjectsContract {
    interface View extends BaseView{
        void initializeAdapterWithProjects(List<Project> projects);

        void addProjectsAtFront(List<Project> projects);

        void addProjectsAtRear(List<Project> projects);
    }

    interface Presenter extends BasePresenter{

        void loadProjects();

        void onProjectsLoaded(List<Project> projects);

        void showError(String errorMessage);
    }
}
