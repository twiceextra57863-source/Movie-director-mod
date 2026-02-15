package com.sdmine.recorder.ui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class SidebarButton {

    public int x, y, width, height;
    public SidebarSection section;
    public boolean hovered;

    public SidebarButton(int x, int y, int width, int height, SidebarSection section) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.section = section;
    }

    public void render(DrawContext ctx, int mouseX, int mouseY, boolean active) {
        hovered = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;

        int bgColor;
        if (active) bgColor = 0xFF3A86FF;
        else if (hovered) bgColor = 0xFF2F2F2F;
        else bgColor = 0xFF1E1E1E;

        ctx.fill(x, y, x + width, y + height, bgColor);

        ctx.drawText(
                ctx.getMatrices().peek().getPositionMatrix(),
                Text.literal(section.name()),
                x + 10,
                y + 8,
                0xFFFFFF
        );
    }

    public boolean isClicked(double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}
