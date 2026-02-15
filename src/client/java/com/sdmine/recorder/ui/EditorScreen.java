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
        addDrawableChild(new SidebarButton(10, 65, "Edit"));

        timeline = new TimelineWidget(0, height - 60, width, 60);
    }

    @Override
    public void render(DrawContext ctx, int mx, int my, float delta) {
        renderBackground(ctx);

        // Preview panel
        ctx.fill(120, 40, width - 20, height - 80, 0xFF202020);
        ctx.drawText(textRenderer, "Preview", 130, 50, 0xFFFFFF, false);

        timeline.render(ctx, mx, my, delta);

        super.render(ctx, mx, my, delta);
    }
}

