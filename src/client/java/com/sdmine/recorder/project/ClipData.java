package com.sdmine.recorder.project;

public class ClipData {

    public enum Type {
        IDLE_CAMERA,
        KEYFRAME_CAMERA
    }

    private Type type;
    private int startTick;
    private int duration;

    public ClipData(Type type, int startTick, int duration) {
        this.type = type;
        this.startTick = startTick;
        this.duration = duration;
    }

    public Type getType() {
        return type;
    }

    public void convertToKeyframe() {
        this.type = Type.KEYFRAME_CAMERA;
    }

    public int getStartTick() {
        return startTick;
    }

    public int getDuration() {
        return duration;
    }
}
