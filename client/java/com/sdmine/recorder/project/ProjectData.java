package com.sdmine.recorder.project;

import java.util.UUID;

public class ProjectData {

    public final String id;
    public String name;
    public int fps;
    public String quality;
    public long createdAt;

    public ProjectData(String name, int fps, String quality) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.fps = fps;
        this.quality = quality;
        this.createdAt = System.currentTimeMillis();
    }
}
