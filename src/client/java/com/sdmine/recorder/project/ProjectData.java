package com.sdmine.recorder.project;

public class ProjectData {

    public String name;
    public long createdAt;
    public long lastOpened;
    public String mode;

    public ProjectData(String name) {
        this.name = name;
        this.createdAt = System.currentTimeMillis();
        this.lastOpened = this.createdAt;
        this.mode = "recording";
    }
}
