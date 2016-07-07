package com.android.locusideas.home.projects.project;

import android.support.annotation.MainThread;
import com.android.locusideas.home.projects.models.Project;

/**
 * Created on 06/07/16.
 */

public class ProjectHolder {
    private Project selectedProject;

    @MainThread
    public void setProject(Project selectedProject){
        this.selectedProject = selectedProject;
    }

    @MainThread
    public Project getProject(){
        return selectedProject;
    }

    @MainThread
    public boolean hasProject(){
        return selectedProject != null;
    }

}
