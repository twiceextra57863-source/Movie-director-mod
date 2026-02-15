package com.sdmine.recorder.project;

public class ClipData {

    public int startTick;
    public int endTick;
    public String name;

    public ClipData(String name, int startTick, int endTick) {
        this.name = name;
        this.startTick = startTick;
        this.endTick = endTick;
    }

    public int length() {
        return endTick - startTick;
    }
}

