package com.sdmine.recorder.recorder;

import com.sdmine.recorder.client.RecorderState;

public class RecorderManager {

    private static RecorderState state = RecorderState.IDLE;
    private static RecordingSession session;

    public static RecorderState getState() {
        return state;
    }

    public static void start() {
        session = new RecordingSession();
        session.start();
        state = RecorderState.RECORDING;
    }

    public static void pause() {
        if (state == RecorderState.RECORDING) {
            session.pause();
            state = RecorderState.PAUSED;
        }
    }

    public static void resume() {
        if (state == RecorderState.PAUSED) {
            session.resume();
            state = RecorderState.RECORDING;
        }
    }

    public static void stop() {
        if (session != null) {
            session.stop();
        }
        state = RecorderState.IDLE;
    }

    public static RecordingSession getSession() {
        return session;
    }
}
