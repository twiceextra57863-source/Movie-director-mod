package com.sdmine.recorder.project;

public class ClipData {

    public enum Type {
        IDLE_CAMERA,
        KEYFRAME_CAMERA
    }

    private String name;
    private Type type;
    private int startTick;
    private int duration;

    public ClipData(String name, Type type, int startTick, int duration) {
        this.name = name;
        this.type = type;
        this.startTick = startTick;
        this.duration = duration;
    }

    public boolean isKeyframe() {
        return type == Type.KEYFRAME_CAMERA;
    }

    public void convertToKeyframe() {
        this.type = Type.KEYFRAME_CAMERA;
        this.name = "Keyframe Camera";
    }

    public int length() {
        return duration;
    }

    public String getName() {
        return name;
    }
}
