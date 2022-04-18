package com.fengmaster.game.base.world.gen;

import com.fengmaster.game.base.obj.BaseGameComponent;
import com.fengmaster.game.base.world.World;
import com.fengmaster.game.base.world.node.WorldNode;

import java.util.Map;

/**
 *
 */
public interface BaseWorldGenerator {

    public Map<Integer,Map<Integer, Map<Integer, BaseGameComponent>>> generateObj(World world);

    public Map<Integer,Map<Integer, Map<Integer, WorldNode>>> generateWorldNode(World world);

}
