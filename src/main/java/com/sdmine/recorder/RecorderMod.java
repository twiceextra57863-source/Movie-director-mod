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
    public static final String MOD_ID = "action-recorder";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    // Keybinding for opening dashboard
    public static KeyBinding openDashboardKey;
    
    @Override
    public void onInitialize() {
        LOGGER.info("Action Recorder Mod initializing...");
        
        // Register keybinding
        openDashboardKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.action-recorder.open_dashboard",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_T,
            "category.action-recorder.general"
        ));
        
        LOGGER.info("Action Recorder Mod initialized!");
    }
}
