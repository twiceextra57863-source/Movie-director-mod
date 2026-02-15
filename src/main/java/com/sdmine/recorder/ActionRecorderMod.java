package com.sdmine.recorder;

import com.sdmine.recorder.command.RecorderCommand;
import com.sdmine.recorder.events.BlockListener;
import com.sdmine.recorder.events.PlayerMoveListener;
import net.fabricmc.api.ModInitializer;

public class ActionRecorderMod implements ModInitializer {

    @Override
    public void onInitialize() {
        RecorderCommand.register();
        PlayerMoveListener.register();
        BlockListener.register();
        System.out.println("Action Recorder Loaded");
    }
}
