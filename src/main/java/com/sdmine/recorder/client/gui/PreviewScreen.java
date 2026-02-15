package com.sdmine.recorder.client.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class PreviewScreen extends Screen {

    public PreviewScreen() {
        super(Text.literal("Recording Preview"));
    }

    @Override
    protected void init() {
        addDrawableChild(
            ButtonWidget.builder(Text.literal("▶ Play"), b -> {})
                .dimensions(width / 2 - 40, height - 40, 80, 20)
                .build()
        );
    }

    @Override
    public void render(net.minecraft.client.gui.DrawContext ctx, int mouseX, int mouseY, float delta) {
        renderBackground(ctx);
        ctx.drawCenteredTextWithShadow(textRenderer, "Timeline Preview (Coming Soon)", width / 2, 40, 0xFFFFFF);
        super.render(ctx, mouseX, mouseY, delta);
    }
}

