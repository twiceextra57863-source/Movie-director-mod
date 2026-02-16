package com.sdmine.recorder.ui.widgets;

import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;

public class TimelineWidget {

    private final int x, y, w, h;
    private final List<TimelineClipWidget> clips = new ArrayList<>();

    public TimelineWidget(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        // DEFAULT IDLE CAMERA CLIP
        addClip(new ClipData("Idle Camera", 0, 200));
    }

    private void addClip(ClipData data) {
        int clipWidth = data.length();
        int clipX = x + 10 + clips.size() * (clipWidth + 5);
        int clipY = y + 10;

        TimelineClipWidget widget =
                new TimelineClipWidget(clipX, clipY, clipWidth, 20, data);

        clips.add(widget);
    }

    public void render(DrawContext ctx, int mouseX, int mouseY) {
        ctx.fill(x, y, x + w, y + h, 0xFF111111);

        for (TimelineClipWidget clip : clips) {
            clip.render(ctx, mouseX, mouseY);
        }
    }

    public void mouseClicked(double mouseX, double mouseY) {
        for (TimelineClipWidget clip : clips) {
            clip.mouseClicked(mouseX, mouseY);
        }
    }
}
