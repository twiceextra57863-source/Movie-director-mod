package com.sdmine.recorder.project;

public class ProjectData {

    public String name;
    public long createdAt;
    public long lastOpened;
    public int fps;
    public String quality;
    public String template;

    public ProjectData(String name) {
        this.name = name;
        this.createdAt = System.currentTimeMillis();
        this.lastOpened = createdAt;
    }
}
