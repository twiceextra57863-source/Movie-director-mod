package com.sdmine.recorder.project;

import java.util.ArrayList;
import java.util.List;

public class ProjectManager {

    private static final List<ProjectData> PROJECTS = new ArrayList<>();

    public static void init() {
        PROJECTS.clear();
    }

    public static void save(ProjectData project) {
        if (!PROJECTS.contains(project)) {
            PROJECTS.add(project);
        }
    }

    public static List<ProjectData> loadProjects() {
        return PROJECTS;
    }
}
