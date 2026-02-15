package com.sdmine.recorder;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.gui.DrawContext;

public class RecordingControlsScreen extends Screen {
    private boolean isRecording = false;
    private boolean isPaused = false;
    
    public RecordingControlsScreen() {
        super(Text.literal("Recording Controls"));
    }
    
    @Override
    protected void init() {
        super.init();
        
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        
        // Start Button
        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("START"),
            button -> {
                this.isRecording = true;
                this.isPaused = false;
                RecorderMod.LOGGER.info("Recording started");
                // Add actual recording logic here
            }
        ).dimensions(centerX - 150, centerY - 30, 90, 20).build());
        
        // Stop Button
        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("STOP"),
            button -> {
                this.isRecording = false;
                this.isPaused = false;
                RecorderMod.LOGGER.info("Recording stopped");
                // Add stop recording logic here
            }
        ).dimensions(centerX - 45, centerY - 30, 90, 20).build());
        
        // Pause Button
        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("PAUSE"),
            button -> {
                this.isPaused = !this.isPaused;
                RecorderMod.LOGGER.info("Recording paused: " + this.isPaused);
                // Add pause logic here
            }
        ).dimensions(centerX + 60, centerY - 30, 90, 20).build());
        
        // Back Button
        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Back"),
            button -> {
                if (this.client != null) {
                    this.client.setScreen(new MainDashboardScreen());
                }
            }
        ).dimensions(10, this.height - 30, 100, 20).build());
    }
    
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        
        // Draw status indicator
        String status;
        int color;
        
        if (isRecording) {
            if (isPaused) {
                status = "Status: PAUSED";
                color = 0xFFAA00; // Orange
            } else {
                status = "Status: RECORDING";
                color = 0xFF5555; // Red
            }
        } else {
            status = "Status: READY";
            color = 0x55FF55; // Green
        }
        
        context.drawText(this.textRenderer, status, this.width / 2 - 50, this.height / 2 + 20, color, true);
        
        super.render(context, mouseX, mouseY, delta);
    }
    
    @Override
    public boolean shouldPause() {
        return false;
    }
}
