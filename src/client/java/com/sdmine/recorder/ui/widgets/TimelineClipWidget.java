package com.sdmine.recorder.ui.widgets;

import com.sdmine.recorder.project.ClipData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class TimelineClipWidget {

    private final ClipData clip;
    private int x, y, width, height;
    private boolean selected = false;
    private ClipContextMenu menu;

    public TimelineClipWidget(ClipData clip, int x, int y, int width, int height) {
        this.clip = clip;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render(DrawContext ctx, int mouseX, int mouseY) {
        int color;

        if (clip.isKeyframe()) color = 0xFF3F51B5;      // blue
        else color = 0xFF555555;                        // idle gray

        if (selected) color = 0xFF4CAF50;

        ctx.fill(x, y, x + width, y + height, color);

        ctx.drawText(
                MinecraftClient.getInstance().textRenderer,
                clip.name,
                x + 5,
                y + 5,
                0xFFFFFF,
                false
        );

        // 3-dot menu
        ctx.drawText(
                MinecraftClient.getInstance().textRenderer,
                "⋮",
                x + width - 10,
                y + 4,
                0xFFFFFF,
                false
        );

        if (menu != null && menu.isVisible()) {
            menu.render(ctx);
        }
    }

    public void mouseClicked(double mouseX, double mouseY) {
        selected =
                mouseX >= x && mouseX <= x + width &&
                mouseY >= y && mouseY <= y + height;

        // click on 3 dots
        if (selected &&
                mouseX >= x + width - 15 && mouseX <= x + width &&
                mouseY >= y && mouseY <= y + height) {

            menu = new ClipContextMenu(clip, x + width + 5, y);
        }

        if (menu != null) {
            menu.mouseClicked(mouseX, mouseY);
        }
    }
}
