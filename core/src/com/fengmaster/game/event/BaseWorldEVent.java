package com.fengmaster.game.event;

import com.fengmaster.game.base.world.World;
import lombok.Getter;

public abstract class BaseWorldEVent extends BaseEvent{

    @Getter
    private World world;

    public BaseWorldEVent(long time, Object sender,World world) {
        super(time, sender);
        this.world=world;
    }
}
