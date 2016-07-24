package com.android.locusideas.home.projects;

import com.android.locusideas.home.projects.models.Project;
import java.util.List;

/**
 * Created on 05/07/16.
 */

public interface ProjectsView {
    void initializeAdapterWithProjects(List<Project> projects);

    void addProjectsAtFront(List<Project> projects);

    void addProjectsAtRear(List<Project> projects);
}
