package com.sdmine.recorder.project;

public class ProjectData {

    public String name;
    public int fps;
    public String quality;
    public String template;

    public ProjectData(String name) {
        this.name = name;
        this.fps = 60;
        this.quality = "High";
        this.template = "Default";
    }
}
