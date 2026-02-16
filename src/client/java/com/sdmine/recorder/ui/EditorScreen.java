package com.sdmine.recorder.ui;

import com.sdmine.recorder.ui.widgets.SidebarButton;
import com.sdmine.recorder.ui.widgets.TimelineWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class EditorScreen extends Screen {

    private TimelineWidget timeline;

    public EditorScreen() {
        super(Text.literal("Movie Director - Editor"));
    }

    @Override
    protected void init() {
        // Sidebar buttons
        addDrawableChild(new SidebarButton(10, 40, "Record"));
        addDrawableChild(new SidebarButton(10, 65, "Cut"));
        addDrawableChild(new SidebarButton(10, 90, "Delete"));
        addDrawableChild(new SidebarButton(10, 115, "Export"));

        // Timeline (bottom area)
        timeline = new TimelineWidget(
                120,                 // x (after sidebar)
                height - 80,         // y
                width - 130,         // width
                70                   // height
        );
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (timeline != null) {
            timeline.mouseClicked(mouseX, mouseY);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        // Background
        renderBackground(ctx);

        // Sidebar background
        ctx.fill(0, 0, 120, height, 0xFF151515);

        // Preview panel
        ctx.fill(130, 30, width - 20, height - 90, 0xFF202020);
        ctx.drawText(
                textRenderer,
                "Camera Preview",
                140,
                40,
                0xFFFFFF,
                false
        );

        // Timeline
        if (timeline != null) {
            timeline.render(ctx, mouseX, mouseY);
        }

        super.render(ctx, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
