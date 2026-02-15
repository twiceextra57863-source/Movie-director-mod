package com.sdmine.recorder;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class MainDashboardScreen extends Screen {
    private static final int LEFT_PANEL_WIDTH = 160;
    private static final Identifier DASHBOARD_BACKGROUND = Identifier.of("action-recorder", "textures/gui/dashboard_bg.png");
    
    // State for different sections
    private boolean recordingOptionsVisible = false;
    private String selectedSection = "none";
    
    public MainDashboardScreen() {
        super(Text.literal("Movie Director Dashboard"));
    }
    
    @Override
    protected void init() {
        super.init();
        
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        
        // === LEFT VERTICAL MENU ===
        
        // Title for left panel
        addDrawableChild(ButtonWidget.builder(
            Text.literal("≡ MENU").styled(style -> style.withBold(true)),
            button -> {}
        ).dimensions(10, 15, LEFT_PANEL_WIDTH - 20, 20).build());
        
        // Separator line (visual only)
        
        // MAIN MENU ITEMS - Vertical list
        // 1. RAW RECORDING (with indicator)
        addDrawableChild(ButtonWidget.builder(
            Text.literal("● RAW RECORDING").styled(style -> 
                style.withColor(recordingOptionsVisible ? 0x55FF55 : 0xFFFFFF)),
            button -> {
                // Toggle recording options
                recordingOptionsVisible = !recordingOptionsVisible;
                if (recordingOptionsVisible) {
                    selectedSection = "recording";
                } else {
                    selectedSection = "none";
                }
                // Refresh to show/hide recording controls
                this.clearAndInit();
            }
        ).dimensions(20, 45, LEFT_PANEL_WIDTH - 40, 20).build());
        
        // Recording sub-options (visible only when RAW RECORDING is selected)
        if (recordingOptionsVisible) {
            // Start Recording button
            addDrawableChild(ButtonWidget.builder(
                Text.literal("   ▶ START NEW RECORDING"),
                button -> {
                    if (this.client != null) {
                        this.client.setScreen(new RecordingControlsScreen("new"));
                    }
                }
            ).dimensions(30, 70, LEFT_PANEL_WIDTH - 50, 20).build());
            
            // Manage Recordings button
            addDrawableChild(ButtonWidget.builder(
                Text.literal("   📁 MANAGE RECORDINGS"),
                button -> {
                    // Will open recordings list
                    RecorderMod.LOGGER.info("Open recordings list");
                }
            ).dimensions(30, 95, LEFT_PANEL_WIDTH - 50, 20).build());
            
            // Settings button
            addDrawableChild(ButtonWidget.builder(
                Text.literal("   ⚙ RECORDING SETTINGS"),
                button -> {
                    // Will open settings
                    RecorderMod.LOGGER.info("Open recording settings");
                }
            ).dimensions(30, 120, LEFT_PANEL_WIDTH - 50, 20).build());
        }
        
        // 2. CAMERA CONTROLS
        addDrawableChild(ButtonWidget.builder(
            Text.literal("● CAMERA CONTROLS"),
            button -> {
                selectedSection = "camera";
                RecorderMod.LOGGER.info("Camera section selected");
            }
        ).dimensions(20, recordingOptionsVisible ? 150 : 70, LEFT_PANEL_WIDTH - 40, 20).build());
        
        // 3. SCENE MANAGEMENT
        addDrawableChild(ButtonWidget.builder(
            Text.literal("● SCENE MANAGER"),
            button -> {
                selectedSection = "scene";
                RecorderMod.LOGGER.info("Scene section selected");
            }
        ).dimensions(20, recordingOptionsVisible ? 175 : 95, LEFT_PANEL_WIDTH - 40, 20).build());
        
        // 4. RENDER OPTIONS
        addDrawableChild(ButtonWidget.builder(
            Text.literal("● RENDER QUEUE"),
            button -> {
                selectedSection = "render";
                RecorderMod.LOGGER.info("Render section selected");
            }
        ).dimensions(20, recordingOptionsVisible ? 200 : 120, LEFT_PANEL_WIDTH - 40, 20).build());
        
        // === RIGHT PANEL - Dynamic content based on selection ===
        
        // Title for right panel
        addDrawableChild(ButtonWidget.builder(
            Text.literal(getPanelTitle()).styled(style -> style.withBold(true)),
            button -> {}
        ).dimensions(LEFT_PANEL_WIDTH + 20, 15, 200, 20).build());
        
        // Dynamic content area
        if (selectedSection.equals("recording") && recordingOptionsVisible) {
            // Show recording info
            addDrawableChild(ButtonWidget.builder(
                Text.literal("No active recording"),
                button -> {}
            ).dimensions(LEFT_PANEL_WIDTH + 30, 50, 200, 20).build());
            
            // Quick record button
            addDrawableChild(ButtonWidget.builder(
                Text.literal("⏺ QUICK RECORD"),
                button -> {
                    if (this.client != null) {
                        this.client.setScreen(new RecordingControlsScreen("quick"));
                    }
                }
            ).dimensions(LEFT_PANEL_WIDTH + 30, 80, 150, 20).build());
        }
        
        // Close button (bottom right)
        addDrawableChild(ButtonWidget.builder(
            Text.literal("✕ Close"),
            button -> {
                if (this.client != null) {
                    this.client.setScreen(null);
                }
            }
        ).dimensions(this.width - 90, this.height - 30, 80, 20).build());
    }
    
    private String getPanelTitle() {
        switch (selectedSection) {
            case "recording": return "RECORDING CONTROL";
            case "camera": return "CAMERA SETTINGS";
            case "scene": return "SCENE MANAGER";
            case "render": return "RENDER QUEUE";
            default: return "WELCOME";
        }
    }
    
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Render dark background
        this.renderBackground(context, mouseX, mouseY, delta);
        
        // Draw left panel background (semi-transparent dark)
        context.fill(0, 0, LEFT_PANEL_WIDTH, this.height, 0xCC111111);
        
        // Draw right panel background (more transparent)
        context.fill(LEFT_PANEL_WIDTH, 0, this.width, this.height, 0x88000000);
        
        // Draw separator line between panels
        context.fill(LEFT_PANEL_WIDTH - 1, 0, LEFT_PANEL_WIDTH, this.height, 0x55AAAAAA);
        
        // Draw header with mod name
        context.drawText(this.textRenderer, "ACTION RECORDER v1.0", LEFT_PANEL_WIDTH + 20, 40, 0xFFFFAA, true);
        
        // Draw help text at bottom
        String helpText = "Press T again to close • Use mouse to navigate";
        context.drawText(this.textRenderer, helpText, LEFT_PANEL_WIDTH + 20, this.height - 40, 0xAAAAAA, false);
        
        super.render(context, mouseX, mouseY, delta);
    }
    
    @Override
    public boolean shouldPause() {
        return false; // Don't pause game
    }
    
    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
    
    private void clearAndInit() {
        this.clearChildren();
        this.init();
    }
}
