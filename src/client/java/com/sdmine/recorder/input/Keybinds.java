package com.sdmine.recorder.input;

import com.sdmine.recorder.ui.DashboardScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Keybinds {

    private static KeyBinding OPEN_DASHBOARD;

    public static void register() {
        OPEN_DASHBOARD = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.action_recorder.dashboard",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_T,
                        "category.action_recorder"
                )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (OPEN_DASHBOARD.wasPressed()) {
                MinecraftClient.getInstance()
                        .setScreen(new DashboardScreen());
            }
        });
    }
}

