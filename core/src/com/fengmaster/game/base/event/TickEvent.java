package com.fengmaster.game.base.event;

import com.fengmaster.game.base.world.World;

/**
 * 时间流逝事件
 */
public class TickEvent extends BaseWorldEVent{

    public TickEvent(long time, Object sender, World world) {
        super(time, sender, world);
    }
}
