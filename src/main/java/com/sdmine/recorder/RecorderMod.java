package com.sdmine.recorder;

import com.sdmine.recorder.input.Keybinds;
import net.fabricmc.api.ClientModInitializer;

public class RecorderMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Keybinds.register();
    }
}

