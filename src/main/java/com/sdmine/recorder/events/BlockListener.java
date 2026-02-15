package com.sdmine.recorder.events;

import com.sdmine.recorder.data.ActionFrame;
import com.sdmine.recorder.data.FrameType;
import com.sdmine.recorder.recorder.RecorderManager;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;

public class BlockListener {

    public static void register() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, be) -> {
            if (RecorderManager.getSession() == null) return;

            RecorderManager.getSession().record(
                new ActionFrame(
                    FrameType.BLOCK_BREAK,
                    pos.getX(),
                    pos.getY(),
                    pos.getZ()
                )
            );
        });
    }
}

