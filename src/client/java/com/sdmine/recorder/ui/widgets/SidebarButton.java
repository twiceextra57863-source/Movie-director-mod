package com.sdmine.recorder.ui.widgets;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

public class SidebarButton extends ClickableWidget {

    private final SidebarSection section;

    public SidebarButton(int x, int y, int width, int height, SidebarSection section) {
        super(x, y, width, height, Text.literal(section.name()));
        this.section = section;
    }

    @Override
    protected void renderWidget(DrawContext ctx, int mouseX, int mouseY, float delta) {
        int color = isHovered() ? 0xFF555555 : 0xFF333333;
        ctx.fill(getX(), getY(), getX() + width, getY() + height, color);

        ctx.drawText(
                MinecraftClient.getInstance().textRenderer,
                getMessage(),
                getX() + 6,
                getY() + 6,
                0xFFFFFF,
                false
        );
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
    }
}
