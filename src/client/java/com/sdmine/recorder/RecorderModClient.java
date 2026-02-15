package com.sdmine.recorder;

import com.sdmine.recorder.project.ProjectManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class RecorderModClient implements ClientModInitializer {

    private static KeyBinding openDashboardKey;

    @Override
    public void onInitializeClient() {

        // Init project system
        try {
            ProjectManager.init();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // T key to open dashboard
        openDashboardKey = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.action_recorder.open_dashboard",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_T,
                        "category.action_recorder"
                )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openDashboardKey.wasPressed()) {
                if (client.isInSingleplayer()) {
                    client.setScreen(new SoftwareDashboardScreen());
                }
            }
        });
    }
}
