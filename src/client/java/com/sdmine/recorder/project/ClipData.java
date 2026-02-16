package com.sdmine.recorder.project;

public class ClipData {

    public enum ClipType {
        IDLE_CAMERA,
        KEYFRAME_CAMERA
    }

    private ClipType type;
    private int startTick;
    private int duration;

    public ClipData(ClipType type, int startTick, int duration) {
        this.type = type;
        this.startTick = startTick;
        this.duration = duration;
    }

    public ClipType getType() {
        return type;
    }

    public void convertToKeyframe() {
        this.type = ClipType.KEYFRAME_CAMERA;
    }

    public int getStartTick() {
        return startTick;
    }

    public int getDuration() {
        return duration;
    }
}

