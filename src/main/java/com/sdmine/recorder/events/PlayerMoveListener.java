package com.sdmine.recorder.events;

import com.sdmine.recorder.data.ActionFrame;
import com.sdmine.recorder.data.FrameType;
import com.sdmine.recorder.recorder.RecorderManager;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class PlayerMoveListener {

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            if (RecorderManager.getSession() == null) return;

            server.getPlayerManager().getPlayerList().forEach(player -> {
                RecorderManager.getSession().record(
                    new ActionFrame(
                        FrameType.MOVE,
                        player.getX(),
                        player.getY(),
                        player.getZ()
                    )
                );
            });
        });
    }
}
