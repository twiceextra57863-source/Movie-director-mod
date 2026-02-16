package com.sdmine.recorder.project;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectData {

    private final String id;
    public String name;
    public int fps;
    public String quality;
    public String template;

    private final List<ClipData> clips = new ArrayList<>();

    public ProjectData(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public void addClip(ClipData clip) {
        clips.add(clip);
    }

    public List<ClipData> getClips() {
        return clips;
    }
}
