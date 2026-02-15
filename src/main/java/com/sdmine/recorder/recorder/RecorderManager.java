package com.sdmine.recorder.recorder;

public class RecorderManager {

    private static RecordingSession session;

    public static void start() {
        session = new RecordingSession();
        session.start();
    }

    public static void pause() {
        if (session != null) session.pause();
    }

    public static void stop() {
        if (session != null) session.stop();
    }

    public static RecordingSession getSession() {
        return session;
    }
}

