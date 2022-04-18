package com.fengmaster.game.base.world.gen;

import cn.hutool.core.collection.CollectionUtil;
import com.fengmaster.game.base.obj.BaseGameComponent;
import com.fengmaster.game.base.obj.Grass;
import com.fengmaster.game.base.obj.PhysicsComponent;
import com.fengmaster.game.base.world.Point3D;
import com.fengmaster.game.base.world.World;
import com.fengmaster.game.base.world.node.WorldNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PureWorldGenerator implements BaseWorldGenerator{

    private long length=50;
    private long width=50;
    private long height=50;

    @Override
    public Map<Integer, Map<Integer, Map<Integer, BaseGameComponent>>> generateObj(World world) {
        Map<Integer, Map<Integer, Map<Integer, BaseGameComponent>>> map=new HashMap<>();
        for (int x = 0; x < length; x++) {
            map.put(x,new HashMap<>());
            for (int y = 0; y < width; y++) {
                map.get(x).put(y,new HashMap<>());
                Grass grass = new Grass();
                List<PhysicsComponent> physics = grass.getComponent("physics", PhysicsComponent.class);
                if (CollectionUtil.isNotEmpty(physics)){
                    physics.get(0).setCenter(new Point3D(x,y,0));
                }
                map.get(x).get(y).put(0,grass);
            }
        }

        return null;
    }

    @Override
    public Map<Integer, Map<Integer, Map<Integer, WorldNode>>> generateWorldNode(World world) {
        Map<Integer, Map<Integer, Map<Integer, WorldNode>>> worldNodeMap=new HashMap<>();

        for (int x = 0; x < length; x++) {
            worldNodeMap.put(x,new HashMap<>());
            for (int y = 0; y < width; y++) {
                worldNodeMap.get(x).put(y,new HashMap<>());
                for (int z = 0; z < height; z++) {
                    worldNodeMap.get(x).get(y).put(z,new WorldNode(20,50,1000));
                }
            }
        }
        return worldNodeMap;
    }
}
