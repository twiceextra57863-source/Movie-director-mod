package com.sdmine.recorder.data;

public class ActionFrame {

    public final FrameType type;
    public final double x, y, z;
    public final long time;

    public ActionFrame(FrameType type, double x, double y, double z) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.z = z;
        this.time = System.currentTimeMillis();
    }
}

