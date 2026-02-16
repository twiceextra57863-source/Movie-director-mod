package com.sdmine.recorder.ui.widgets;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;

public class SidebarButton extends ClickableWidget {

    public final SidebarSection section;

    public SidebarButton(int x, int y, int w, int h, SidebarSection section) {
        super(x, y, w, h, Text.literal(section.name()));
        this.section = section;
    }

    @Override
    protected void renderButton(DrawContext ctx, int mouseX, int mouseY, float delta) {
        int color = isHovered() ? 0xFF444444 : 0xFF222222;
        ctx.fill(getX(), getY(), getX() + width, getY() + height, color);
        ctx.drawTextWithShadow(
                client.textRenderer,
                getMessage(),
                getX() + 6,
                getY() + 6,
                0xFFFFFF
        );
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {
        appendDefaultNarrations(builder);
    }
}
