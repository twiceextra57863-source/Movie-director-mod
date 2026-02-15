package com.sdmine.recorder.ui.widgets;

import net.minecraft.client.gui.DrawContext;

public class TimelineWidget {

    private final int x, y, w, h;

    public TimelineWidget(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void render(DrawContext ctx, int mx, int my, float delta) {
        ctx.fill(x, y, x + w, y + h, 0xFF111111);

        for (int i = 0; i < w; i += 40) {
            ctx.fill(x + i, y, x + i + 1, y + h, 0xFF333333);
        }

        ctx.drawText(
                net.minecraft.client.MinecraftClient.getInstance().textRenderer,
                "Timeline",
                x + 10,
                y + 10,
                0xFFFFFF,
                false
        );
    }
}

