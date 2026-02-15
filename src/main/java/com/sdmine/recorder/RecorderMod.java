package com.sdmine.recorder;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecorderMod implements ModInitializer {
    public static final String MOD_ID = "movie-director";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    // Keybinding for opening dashboard
    private static KeyBinding openDashboardKey;
    
    @Override
    public void onInitialize() {
        LOGGER.info("Movie Director Mod initializing...");
        
        // Register keybinding
        openDashboardKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.movie-director.open_dashboard",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_T,
            "category.movie-director.general"
        ));
        
        // Register key input handler
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openDashboardKey.wasPressed()) {
                // Will open dashboard screen when T is pressed
                if (client.player != null) {
                    LOGGER.info("Opening dashboard...");
                    // We'll implement screen opening later
                }
            }
        });
        
        LOGGER.info("Movie Director Mod initialized successfully!");
    }
}
