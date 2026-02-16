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

        ClipData data = new ClipData("Idle Camera", 0, 200);
        addClip(data);
    }

    private void addClip(ClipData data) {
        int clipWidth = data.length();
        int clipX = x + 10;
        int clipY = y + 10;

        clips.add(new TimelineClipWidget(
                data,
                clipX,
                clipY,
                clipWidth,
                20
        ));
    }

    public void render(DrawContext ctx, int mouseX, int mouseY) {
        ctx.fill(x, y, x + w, y + h, 0xFF111111);
        for (TimelineClipWidget clip : clips) {
            clip.render(ctx, mouseX, mouseY);
        }
    }
}
