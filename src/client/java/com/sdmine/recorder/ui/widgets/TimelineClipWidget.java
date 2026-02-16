package com.sdmine.recorder.ui.widgets;

import net.minecraft.client.gui.DrawContext;

public class TimelineClipWidget {

    private final ClipData clip;
    private final int x, y, w, h;

    public TimelineClipWidget(ClipData clip, int x, int y, int w, int h) {
        this.clip = clip;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void render(DrawContext ctx, int mouseX, int mouseY) {
        int color = clip.isKeyframe() ? 0xFF3F51B5 : 0xFF555555;
        ctx.fill(x, y, x + w, y + h, color);
        ctx.drawTextWithShadow(
                net.minecraft.client.MinecraftClient.getInstance().textRenderer,
                clip.getName(),
                x + 4,
                y + 6,
                0xFFFFFF
        );
    }
}
