package com.sdmine.recorder.ui;

import com.sdmine.recorder.ui.widgets.SidebarButton;
import com.sdmine.recorder.ui.widgets.TimelineWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class EditorScreen extends Screen {

    private TimelineWidget timeline;

    public EditorScreen() {
        super(Text.literal("Editor"));
    }

    @Override
    protected void init() {
        addDrawableChild(new SidebarButton(10, 40, "Record"));
        addDrawableChild(new SidebarButton(10, 65, "Cut"));
        addDrawableChild(new SidebarButton(10, 90, "Delete"));

        timeline = new TimelineWidget(0, height - 70, width, 70);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        timeline.mouseClicked(mouseX, mouseY);
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void render(DrawContext ctx, int mx, int my, float delta) {
        renderBackground(ctx);

        // Preview panel
        ctx.fill(120, 30, width - 20, height - 90, 0xFF202020);
        ctx.drawText(textRenderer, "Camera Preview", 130, 40, 0xFFFFFF, false);

        timeline.render(ctx, mx, my);

        super.render(ctx, mx, my, delta);
    }
}
