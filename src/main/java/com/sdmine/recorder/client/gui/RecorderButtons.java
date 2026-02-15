package com.sdmine.recorder.client.gui;

import com.sdmine.recorder.client.RecorderState;
import com.sdmine.recorder.recorder.RecorderManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

/**
 * Handles creation of recorder buttons inside the Game Menu (Save & Quit screen)
 */
public class RecorderButtons {

    /**
     * Adds recorder buttons based on current recorder state
     */
    public static void add(Screen screen) {
        MinecraftClient mc = MinecraftClient.getInstance();

        // Safe position (right side, no vanilla button overlap)
        int x = screen.width / 2 + 110;
        int y = screen.height / 4 + 8;
        int w = 88;
        int h = 20;

        RecorderState state = RecorderManager.getState();

        // ❌ NOT RECORDING → START + PREVIEW
        if (state == RecorderState.IDLE) {

            screen.addDrawableChild(
                ButtonWidget.builder(Text.literal("▶ Start"), button -> {
                    RecorderManager.start();
                }).dimensions(x, y, w, h).build()
            );

            screen.addDrawableChild(
                ButtonWidget.builder(Text.literal("🅣 Preview"), button -> {
                    mc.setScreen(new PreviewScreen());
                }).dimensions(x, y + 24, w, h).build()
            );
        }

        // ▶ RECORDING → PAUSE + STOP
        else if (state == RecorderState.RECORDING) {

            screen.addDrawableChild(
                ButtonWidget.builder(Text.literal("⏸ Pause"), button -> {
                    RecorderManager.pause();
                }).dimensions(x, y, w, h).build()
            );

            screen.addDrawableChild(
                ButtonWidget.builder(Text.literal("⏹ Stop"), button -> {
                    RecorderManager.stop();
                }).dimensions(x, y + 24, w, h).build()
            );
        }

        // ⏸ PAUSED → RESUME + STOP
        else if (state == RecorderState.PAUSED) {

            screen.addDrawableChild(
                ButtonWidget.builder(Text.literal("▶ Resume"), button -> {
                    RecorderManager.resume();
                }).dimensions(x, y, w, h).build()
            );

            screen.addDrawableChild(
                ButtonWidget.builder(Text.literal("⏹ Stop"), button -> {
                    RecorderManager.stop();
                }).dimensions(x, y + 24, w, h).build()
            );
        }
    }
}
