package com.sdmine.recorder.ui.widgets;

import com.sdmine.recorder.project.ClipData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class TimelineClipWidget {

    private final ClipData clip;
    private int x, y, width, height;
    private boolean selected = false;

    public TimelineClipWidget(ClipData clip, int x, int y, int width, int height) {
        this.clip = clip;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render(DrawContext ctx, int mouseX, int mouseY) {
        boolean hover =
                mouseX >= x && mouseX <= x + width &&
                mouseY >= y && mouseY <= y + height;

        int color;
        if (selected) color = 0xFF4CAF50;          // green
        else if (hover) color = 0xFF666666;        // hover
        else color = 0xFF444444;                   // normal

        ctx.fill(x, y, x + width, y + height, color);

        ctx.drawText(
                MinecraftClient.getInstance().textRenderer,
                clip.name,
                x + 5,
                y + 5,
                0xFFFFFF,
                false
        );
    }

    public void mouseClicked(double mouseX, double mouseY) {
        selected =
                mouseX >= x && mouseX <= x + width &&
                mouseY >= y && mouseY <= y + height;
    }
}

