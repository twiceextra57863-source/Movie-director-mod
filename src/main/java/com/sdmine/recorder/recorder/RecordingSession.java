package com.sdmine.recorder.recorder;

import com.sdmine.recorder.data.ActionFrame;
import java.util.ArrayList;
import java.util.List;

public class RecordingSession {

    private final List<ActionFrame> frames = new ArrayList<>();
    private boolean active = false;

    public void start() {
        active = true;
    }

    public void pause() {
        active = false;
    }

    public void stop() {
        active = false;
        System.out.println("Recorded frames: " + frames.size());
    }

    public void record(ActionFrame frame) {
        if (active) frames.add(frame);
    }
}
