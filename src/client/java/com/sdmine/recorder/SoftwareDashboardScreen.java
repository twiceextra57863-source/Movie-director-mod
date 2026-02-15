package com.sdmine.recorder;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class SoftwareDashboardScreen extends Screen {

    private static final int SIDEBAR_WIDTH = 80;
    private static final int TOPBAR_HEIGHT = 30;

    public SoftwareDashboardScreen() {
        super(Text.literal("Action Recorder Dashboard"));
    }

    @Override
    protected void init() {

        // 🔹 Create Project (Top bar button)
        this.addDrawableChild(
                ButtonWidget.builder(
                        Text.literal("+ Create Project"),
                        btn -> {
                            System.out.println("Create Project Clicked");
                        }
                ).dimensions(10, 5, 120, 20).build()
        );

        // 🔹 Recording button (Left sidebar)
        this.addDrawableChild(
                ButtonWidget.builder(
                        Text.literal("Recording"),
                        btn -> {
                            System.out.println("Recording Clicked");
                        }
                ).dimensions(10, TOPBAR_HEIGHT + 10, 60, 20).build()
        );

        // 🔹 Edit button (Left sidebar)
        this.addDrawableChild(
                ButtonWidget.builder(
                        Text.literal("Edit"),
                        btn -> {
                            System.out.println("Edit Clicked");
                        }
                ).dimensions(10, TOPBAR_HEIGHT + 40, 60, 20).build()
        );
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {

        // Background
        this.renderBackground(context);

        // 🔹 Top bar
        context.fill(0, 0, this.width, TOPBAR_HEIGHT, 0xFF1E1E1E);

        // 🔹 Sidebar
        context.fill(0, TOPBAR_HEIGHT, SIDEBAR_WIDTH, this.height, 0xFF2A2A2A);

        // 🔹 Main content area
        context.fill(
                SIDEBAR_WIDTH,
                TOPBAR_HEIGHT,
                this.width,
                this.height,
                0xFF121212
        );

        // Title
        context.drawText(
                this.textRenderer,
                "Action Recorder",
                SIDEBAR_WIDTH + 20,
                TOPBAR_HEIGHT + 20,
                0xFFFFFF,
                false
        );

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}

