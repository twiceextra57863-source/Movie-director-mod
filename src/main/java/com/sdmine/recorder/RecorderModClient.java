package com.sdmine.recorder;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public class RecorderModClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        // Register client tick event for key handling
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // Check if T key was pressed
            while (RecorderMod.openDashboardKey.wasPressed()) {
                // Open the main dashboard screen
                if (client.player != null) {
                    RecorderMod.LOGGER.info("Opening dashboard...");
                    client.setScreen(new MainDashboardScreen());
                }
            }
        });
    }
}
