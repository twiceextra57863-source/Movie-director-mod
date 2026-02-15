package com.sdmine.recorder.project;

public class ClipData {

    public enum ClipType {
        IDLE_CAMERA,
        KEYFRAME_CAMERA
    }

    public String name;
    public int startTick;
    public int endTick;
    public ClipType type;

    public ClipData(String name, int startTick, int endTick) {
        this.name = name;
        this.startTick = startTick;
        this.endTick = endTick;
        this.type = ClipType.IDLE_CAMERA; // default
    }

    public int length() {
        return endTick - startTick;
    }

    public boolean isKeyframe() {
        return type == ClipType.KEYFRAME_CAMERA;
    }

    public void convertToKeyframe() {
        this.type = ClipType.KEYFRAME_CAMERA;
        this.name = "Keyframe Camera";
    }
}
