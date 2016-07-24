package com.android.locusideas.core.data.models.response;

import com.android.locusideas.home.projects.models.Project;

import java.util.List;

/**
 * Created on 30/06/16.
 */
public class ProjectsResponse {
    List<Project> projects;
    Paging paging;

    class Paging{
        int total;
        int limit;
        int page;
        int pages;
    }

    public List<Project> getProjects() {
        return projects;
    }
}
