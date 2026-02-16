package com.sdmine.recorder.ui.widgets;

public class ClipData {

    private final String name;
    private final int start;
    private final int end;
    private boolean keyframe;

    public ClipData(String name, int start, int end) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.keyframe = false;
    }

    public String getName() {
        return name;
    }

    public int length() {
        return end - start;
    }

    public boolean isKeyframe() {
        return keyframe;
    }

    public void convertToKeyframe() {
        this.keyframe = true;
    }
}
