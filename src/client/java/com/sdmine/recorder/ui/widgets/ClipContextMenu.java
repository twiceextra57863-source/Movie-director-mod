package com.sdmine.recorder.ui.widgets;

import com.sdmine.recorder.project.ClipData;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ClipContextMenu {

    private final ClipData clip;
    private final int x, y;
    private boolean visible = true;

    public ClipContextMenu(ClipData clip, int x, int y) {
        this.clip = clip;
        this.x = x;
        this.y = y;
    }

    public void render(DrawContext ctx) {
        if (!visible) return;

        ctx.fill(x, y, x + 140, y + 30, 0xFF222222);

        ctx.drawText(
                net.minecraft.client.MinecraftClient.getInstance().textRenderer,
                "Convert to Keyframe",
                x + 10,
                y + 10,
                0xFFFFFF,
                false
        );
    }

    public void mouseClicked(double mx, double my) {
        if (!visible) return;

        if (mx >= x && mx <= x + 140 && my >= y && my <= y + 30) {
            clip.convertToKeyframe();
            visible = false;
        }
    }

    public boolean isVisible() {
        return visible;
    }
}

