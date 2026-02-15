package com.sdmine.recorder.ui.widgets;

import com.sdmine.recorder.project.ClipData;
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

        // TEMP DEMO CLIPS
        addClip(new ClipData("Intro", 0, 100));
        addClip(new ClipData("Pan Shot", 100, 240));
        addClip(new ClipData("Zoom", 240, 360));
    }

    private void addClip(ClipData data) {
        int clipWidth = data.length(); // 1 tick = 1 px (for now)
        int clipX = x + 10 + clips.size() * 110;

        clips.add(new TimelineClipWidget(
                data,
                clipX,
                y + 15,
                clipWidth,
                30
        ));
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
