package com.android.locusideas.home.projects.project;

import com.android.locusideas.home.projects.models.Project;
import com.android.locusideas.home.projects.models.ProjectMedia;
import java.util.List;

/**
 * Created on 06/07/16.
 */

public interface ProjectView {
    void updateProjectView(Project project);

    void showMedia(List<ProjectMedia> projectMedias);

    void showError(String errorMessage);

    void onToolbarCollapsed();

    void onToolbarExpanded();
}
