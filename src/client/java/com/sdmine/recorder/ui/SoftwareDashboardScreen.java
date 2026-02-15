package com.sdmine.recorder.ui;

import com.sdmine.recorder.project.ProjectData;
import com.sdmine.recorder.project.ProjectManager;
import com.sdmine.recorder.ui.SidebarButton;
import com.sdmine.recorder.ui.SidebarSection;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class SoftwareDashboardScreen extends Screen {

    private static final int SIDEBAR_WIDTH = 80;
    private static final int TOPBAR_HEIGHT = 30;

    private SidebarSection activeSection = SidebarSection.RECORDING;
    private final List<SidebarButton> sidebarButtons = new ArrayList<>();

    public SoftwareDashboardScreen() {
        super(Text.literal("Action Recorder"));
    }

    @Override
    protected void init() {

        sidebarButtons.clear();
        sidebarButtons.add(new SidebarButton(0, 40, 80, 30, SidebarSection.RECORDING));
        sidebarButtons.add(new SidebarButton(0, 75, 80, 30, SidebarSection.EDIT));

        // Create Project button (top bar)
        this.addDrawableChild(
                ButtonWidget.builder(
                        Text.literal("+ Create Project"),
                        btn -> {
                            try {
                                ProjectData project =
                                        new ProjectData("Project_" + System.currentTimeMillis());
                                ProjectManager.save(project);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                ).dimensions(10, 5, 140, 20).build()
        );
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {

        // Background
        this.renderBackground(context);

        // Top bar
        context.fill(0, 0, width, TOPBAR_HEIGHT, 0xFF1C1C1C);

        // Sidebar
        context.fill(0, TOPBAR_HEIGHT, SIDEBAR_WIDTH, height, 0xFF222222);

        // Main area
        context.fill(
                SIDEBAR_WIDTH,
                TOPBAR_HEIGHT,
                width,
                height,
                0xFF121212
        );

        // Sidebar buttons
        for (SidebarButton btn : sidebarButtons) {
            btn.render(context, mouseX, mouseY, btn.section == activeSection);
        }

        // Title
        context.drawText(
                this.textRenderer,
                "Action Recorder - " + activeSection.name(),
                SIDEBAR_WIDTH + 15,
                TOPBAR_HEIGHT + 15,
                0xFFFFFF,
                false
        );

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (SidebarButton btn : sidebarButtons) {
            if (btn.isClicked(mouseX, mouseY)) {
                activeSection = btn.section;
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
