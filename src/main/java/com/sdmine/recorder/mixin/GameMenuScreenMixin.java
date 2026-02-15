package com.sdmine.recorder.mixin;

import com.sdmine.recorder.client.RecorderState;
import com.sdmine.recorder.recorder.RecorderManager;
import com.sdmine.recorder.client.gui.PreviewScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin {

    @Inject(method = "initWidgets", at = @At("TAIL"))
    private void addRecorderButtons(CallbackInfo ci) {
        GameMenuScreen screen = (GameMenuScreen) (Object) this;
        MinecraftClient mc = MinecraftClient.getInstance();

        int x = screen.width / 2 + 110; // RIGHT SIDE (safe zone)
        int y = screen.height / 4 + 8;

        RecorderState state = RecorderManager.getState();

        if (state == RecorderState.IDLE) {
            screen.addDrawableChild(
                ButtonWidget.builder(Text.literal("▶ Start"), b -> RecorderManager.start())
                    .dimensions(x, y, 80, 20)
                    .build()
            );

            screen.addDrawableChild(
                ButtonWidget.builder(Text.literal("🅣 Preview"), b ->
                    mc.setScreen(new PreviewScreen())
                ).dimensions(x, y + 24, 80, 20).build()
            );
        }

        if (state == RecorderState.RECORDING) {
            screen.addDrawableChild(
                ButtonWidget.builder(Text.literal("⏸ Pause"), b -> RecorderManager.pause())
                    .dimensions(x, y, 80, 20)
                    .build()
            );

            screen.addDrawableChild(
                ButtonWidget.builder(Text.literal("⏹ Stop"), b -> RecorderManager.stop())
                    .dimensions(x, y + 24, 80, 20)
                    .build()
            );
        }

        if (state == RecorderState.PAUSED) {
            screen.addDrawableChild(
                ButtonWidget.builder(Text.literal("▶ Resume"), b -> RecorderManager.resume())
                    .dimensions(x, y, 80, 20)
                    .build()
            );

            screen.addDrawableChild(
                ButtonWidget.builder(Text.literal("⏹ Stop"), b -> RecorderManager.stop())
                    .dimensions(x, y + 24, 80, 20)
                    .build()
            );
        }
    }
}
