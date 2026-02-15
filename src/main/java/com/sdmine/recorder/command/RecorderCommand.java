package com.sdmine.recorder.command;

import com.sdmine.recorder.recorder.RecorderManager;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;

public class RecorderCommand {

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registry, env) -> {
            dispatcher.register(CommandManager.literal("record")
                .then(CommandManager.literal("start")
                    .executes(ctx -> {
                        RecorderManager.start();
                        return 1;
                    }))
                .then(CommandManager.literal("pause")
                    .executes(ctx -> {
                        RecorderManager.pause();
                        return 1;
                    }))
                .then(CommandManager.literal("stop")
                    .executes(ctx -> {
                        RecorderManager.stop();
                        return 1;
                    }))
            );
        });
    }
}

