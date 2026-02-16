package com.sdmine.recorder.project;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectData {

    private final String id;
    private String name;
    private final List<ClipData> clips = new ArrayList<>();

    public ProjectData(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ClipData> getClips() {
        return clips;
    }

    public void addClip(ClipData clip) {
        clips.add(clip);
    }
}
