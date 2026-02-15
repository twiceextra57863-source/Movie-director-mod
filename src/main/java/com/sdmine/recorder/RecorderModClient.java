package com.sdmine.recorder;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class RecorderModClient implements ClientModInitializer {
    
    private static KeyBinding openDashboardKey;
    
    @Override
    public void onInitializeClient() {
        // Register keybinding specifically for client
        openDashboardKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.movie-director.open_dashboard",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_T,
            "category.movie-director.general"
        ));
        
        // Client tick event for screen opening
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (openDashboardKey.wasPressed()) {
                // Will open screen here
                client.setScreen(new MainDashboardScreen());
            }
        });
    }
}
