package com.fengmaster.game.event;

import com.fengmaster.game.base.world.World;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * 世界生成事件
 */
@AllArgsConstructor
public class WorldCreatedEvent {
    @Getter
    private World world;
}
