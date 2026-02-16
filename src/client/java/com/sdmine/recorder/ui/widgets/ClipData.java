package com.sdmine.recorder.ui.widgets;

public class ClipData {

    /* =========================
       CLIP TYPE (IMPORTANT)
       ========================= */
    public enum Type {
        IDLE_CAMERA,
        CAMERA_MOVE,
        CUT,
        EFFECT
    }

    /* =========================
       FIELDS
       ========================= */
    public final String name;
    public final Type type;
    public final int start;
    public final int end;

    private boolean keyframe;

    /* =========================
       CONSTRUCTORS
       ========================= */

    // Simple constructor (old usage support)
    public ClipData(String name, int start, int end) {
        this(name, Type.IDLE_CAMERA, start, end);
    }

    // Full constructor (new system)
    public ClipData(String name, Type type, int start, int end) {
        this.name = name;
        this.type = type;
        this.start = start;
        this.end = end;
        this.keyframe = false;
    }

    /* =========================
       METHODS
       ========================= */

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
