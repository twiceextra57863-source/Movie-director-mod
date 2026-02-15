package com.sdmine.recorder.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

/**
 * Client-side initializer for Action Recorder
 * Handles client-only logic like GUI state sync and preview updates
 */
public class RecorderClient implements ClientModInitializer {

    private static MinecraftClient client;

    @Override
    public void onInitializeClient() {
        client = MinecraftClient.getInstance();

        // Client tick hook (future use: preview playback, timeline update)
        ClientTickEvents.END_CLIENT_TICK.register(mc -> {
            if (mc.player == null || mc.world == null) return;

            // 🔮 Reserved for future:
            // - Preview playback
            // - Camera interpolation
            // - Timeline scrub updates
        });

        System.out.println("[ActionRecorder] Client initialized");
    }

    /**
     * Utility access (safe)
     */
    public static MinecraftClient getClient() {
        return client;
    }
}

