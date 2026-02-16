package com.sdmine.recorder.ui.widgets;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;

public class SidebarButton extends ClickableWidget {

    public final SidebarSection section;

    public SidebarButton(int x, int y, int width, int height, SidebarSection section) {
        super(x, y, width, height, Text.literal(section.name()));
        this.section = section;
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        int bg = this.isHovered() ? 0xFF333333 : 0xFF222222;
        context.fill(getX(), getY(), getX() + width, getY() + height, bg);

        context.drawCenteredTextWithShadow(
                MinecraftClient.getInstance().textRenderer,
                this.getMessage(),
                getX() + width / 2,
                getY() + (height - 8) / 2,
                0xFFFFFF
        );
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {
        this.appendDefaultNarrations(builder);
    }
}
