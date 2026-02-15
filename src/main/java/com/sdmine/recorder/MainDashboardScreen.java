package com.sdmine.recorder;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.gui.DrawContext;

public class MainDashboardScreen extends Screen {
    private int leftPanelWidth = 150;
    private boolean recordingOptionsVisible = false;
    
    public MainDashboardScreen() {
        super(Text.literal("Movie Director Dashboard"));
    }
    
    @Override
    protected void init() {
        super.init();
        
        // Left side vertical menu - Raw Recording option
        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Raw Recording"),
            button -> {
                this.recordingOptionsVisible = !this.recordingOptionsVisible;
                this.openRecordingControls();
            }
        ).dimensions(10, 40, 130, 20).build());
        
        // Additional menu options (can be expanded)
        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Camera Settings"),
            button -> {}
        ).dimensions(10, 70, 130, 20).build());
        
        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Render Options"),
            button -> {}
        ).dimensions(10, 100, 130, 20).build());
    }
    
    private void openRecordingControls() {
        if (this.client != null) {
            this.client.setScreen(new RecordingControlsScreen());
        }
    }
    
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Render semi-transparent background
        this.renderBackground(context, mouseX, mouseY, delta);
        
        // Draw left panel background
        context.fill(0, 0, leftPanelWidth, this.height, 0xCC222222);
        
        // Draw title
        context.drawText(this.textRenderer, "Movie Director", 160, 10, 0xFFFFFF, true);
        
        super.render(context, mouseX, mouseY, delta);
    }
    
    @Override
    public boolean shouldPause() {
        return false; // Don't pause game when GUI is open
    }
}
