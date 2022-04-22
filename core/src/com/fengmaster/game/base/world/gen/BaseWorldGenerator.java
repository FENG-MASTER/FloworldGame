package com.fengmaster.game.base.world.gen;

import com.fengmaster.game.base.obj.BaseGameComponent;
import com.fengmaster.game.base.world.World;
import com.fengmaster.game.base.world.node.WorldNode;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface BaseWorldGenerator {

    public Map<Long,Map<Long, Map<Long, List<BaseGameComponent>>>> generateObj(World world);

    public Map<Long,Map<Long, Map<Long, WorldNode>>> generateWorldNode(World world);

}
