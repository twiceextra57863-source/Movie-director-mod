package com.sdmine.recorder;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class RecordingControlsScreen extends Screen {
    private boolean isRecording = false;
    private boolean isPaused = false;
    private String recordingMode;
    private int recordingTime = 0;
    private long lastTick = 0;
    
    public RecordingControlsScreen(String mode) {
        super(Text.literal("Recording Controls"));
        this.recordingMode = mode;
    }
    
    @Override
    protected void init() {
        super.init();
        
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        
        // Title
        addDrawableChild(ButtonWidget.builder(
            Text.literal("RECORDING CONTROLS").styled(style -> style.withBold(true)),
            button -> {}
        ).dimensions(centerX - 100, centerY - 80, 200, 20).build());
        
        // Mode indicator
        String modeText = recordingMode.equals("new") ? "New Recording" : "Quick Record";
        addDrawableChild(ButtonWidget.builder(
            Text.literal("Mode: " + modeText),
            button -> {}
        ).dimensions(centerX - 100, centerY - 50, 200, 20).build());
        
        // === CONTROL BUTTONS IN BLENDER STYLE ===
        
        // START Button (Green)
        addDrawableChild(ButtonWidget.builder(
            Text.literal("▶ START").styled(style -> style.withColor(0x55FF55)),
            button -> {
                this.isRecording = true;
                this.isPaused = false;
                this.lastTick = System.currentTimeMillis();
                RecorderMod.LOGGER.info("Recording started in " + recordingMode + " mode");
            }
        ).dimensions(centerX - 160, centerY, 100, 30).build());
        
        // STOP Button (Red)
        addDrawableChild(ButtonWidget.builder(
            Text.literal("⏹ STOP").styled(style -> style.withColor(0xFF5555)),
            button -> {
                this.isRecording = false;
                this.isPaused = false;
                this.recordingTime = 0;
                RecorderMod.LOGGER.info("Recording stopped. Total time: " + formatTime(recordingTime));
            }
        ).dimensions(centerX - 50, centerY, 100, 30).build());
        
        // PAUSE Button (Yellow)
        addDrawableChild(ButtonWidget.builder(
            Text.literal("⏸ PAUSE").styled(style -> style.withColor(0xFFAA00)),
            button -> {
                this.isPaused = !this.isPaused;
                RecorderMod.LOGGER.info("Recording " + (isPaused ? "paused" : "resumed"));
            }
        ).dimensions(centerX + 60, centerY, 100, 30).build());
        
        // Back button
        addDrawableChild(ButtonWidget.builder(
            Text.literal("← Back to Dashboard"),
            button -> {
                if (this.client != null) {
                    this.client.setScreen(new MainDashboardScreen());
                }
            }
        ).dimensions(10, this.height - 30, 150, 20).build());
    }
    
    @Override
    public void tick() {
        super.tick();
        
        // Update recording time if recording and not paused
        if (isRecording && !isPaused) {
            long currentTime = System.currentTimeMillis();
            if (lastTick > 0) {
                recordingTime += (currentTime - lastTick);
            }
            lastTick = currentTime;
        } else {
            lastTick = 0;
        }
    }
    
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        
        // Draw status box
        String status;
        int statusColor;
        
        if (isRecording) {
            if (isPaused) {
                status = "⏸ PAUSED";
                statusColor = 0xFFAA00;
            } else {
                status = "⏺ RECORDING";
                statusColor = 0xFF5555;
            }
        } else {
            status = "⏹ READY";
            statusColor = 0x55FF55;
        }
        
        // Draw recording timer
        String timeStr = formatTime(recordingTime);
        
        // Background for status
        context.fill(centerX - 75, centerY - 40, centerX + 75, centerY - 15, 0xAA000000);
        
        // Status text
        context.drawText(this.textRenderer, status, centerX - 70, centerY - 35, statusColor, true);
        
        // Timer text
        context.drawText(this.textRenderer, timeStr, centerX - 70, centerY - 25, 0xFFFFFF, true);
        
        // FPS warning if recording (placeholder)
        if (isRecording && !isPaused) {
            context.drawText(this.textRenderer, "Recording in progress...", 10, 10, 0xFFFFAA, true);
        }
        
        super.render(context, mouseX, mouseY, delta);
    }
    
    private String formatTime(int millis) {
        int seconds = (millis / 1000) % 60;
        int minutes = (millis / (1000 * 60)) % 60;
        int hours = (millis / (1000 * 60 * 60));
        
        if (hours > 0) {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format("%02d:%02d", minutes, seconds);
        }
    }
    
    @Override
    public boolean shouldPause() {
        return false;
    }
}
