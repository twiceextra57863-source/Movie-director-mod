package com.sdmine.recorder.ui;

import com.sdmine.recorder.project.ProjectData;
import com.sdmine.recorder.project.ProjectManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.List;

public class DashboardScreen extends Screen {

    public DashboardScreen() {
        super(Text.literal("Action Recorder"));
    }

    @Override
    protected void init() {
        addDrawableChild(
                net.minecraft.client.gui.widget.ButtonWidget.builder(
                        Text.literal("+ Create Project"),
                        btn -> client.setScreen(new ProjectFormScreen())
                ).dimensions(width / 2 - 60, 20, 120, 20).build()
        );
    }

    @Override
    public void render(DrawContext ctx, int mx, int my, float delta) {
        renderBackground(ctx);
        ctx.drawText(textRenderer, "Projects", 20, 20, 0xFFFFFF, false);

        List<ProjectData> projects = ProjectManager.loadProjects();
        int y = 50;

        for (ProjectData p : projects) {
            ctx.drawText(
                    textRenderer,
                    p.name + " | " + p.fps + " FPS | " + p.quality,
                    20,
                    y,
                    0xAAAAAA,
                    false
            );
            y += 15;
        }

        super.render(ctx, mx, my, delta);
    }
}

