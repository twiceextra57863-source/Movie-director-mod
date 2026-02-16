package com.sdmine.recorder.project;

import java.util.ArrayList;
import java.util.List;

public class ProjectManager {

    private static final List<ProjectData> PROJECTS = new ArrayList<>();

    public static void add(ProjectData project) {
        PROJECTS.add(project);
    }

    public static List<ProjectData> getAll() {
        return PROJECTS;
    }
}
