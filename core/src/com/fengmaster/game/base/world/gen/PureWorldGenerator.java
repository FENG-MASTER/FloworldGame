package com.fengmaster.game.base.world.gen;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.RandomUtil;
import com.fengmaster.game.base.obj.BaseGameComponent;
import com.fengmaster.game.base.obj.ability.Fire;
import com.fengmaster.game.base.obj.entity.Cobble;
import com.fengmaster.game.base.obj.entity.Grass;
import com.fengmaster.game.base.obj.PhysicsComponent;
import com.fengmaster.game.base.obj.entity.Soil;
import com.fengmaster.game.base.obj.fluid.Oxygen;
import com.fengmaster.game.base.world.Point3D;
import com.fengmaster.game.base.world.World;
import com.fengmaster.game.base.world.node.WorldNode;
import io.reactivex.rxjava3.core.Flowable;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log
public class PureWorldGenerator implements BaseWorldGenerator {

    private long length = 10;
    private long width = 10;
    private long height = 10;

    @Override
    public Map<Long, Map<Long, Map<Long, List<BaseGameComponent>>>> generateObj(World world) {

        List<Point3D> emptyPoints = new ArrayList<>();

        for (int x = 0; x < length; x++) {
            for (int y = 0; y < width; y++) {
                for (int z = 0; z < height; z++) {
                    emptyPoints.add(new Point3D(x, y, z));
                }
            }

        }


        Map<Long, Map<Long, Map<Long, List<BaseGameComponent>>>> map = new HashMap<>();
        for (long z = 0; z < height; z++) {
            map.put(z, new HashMap<>());
            for (long x = 0; x < length; x++) {
                map.get(z).put(x, new HashMap<>());

                for (long y = 0; y < width; y++) {
                    map.get(z).get(x).put(y, new ArrayList<>());

                }

            }
        }

        for (long y = 0; y < width; y++) {
            for (long x = 0; x < length; x++) {
                PhysicsComponent grass;
                if (RandomUtil.getRandom().nextInt(10) > 7) {
                    grass = new Grass();
                } else {
                    grass = new Soil();
                }
                emptyPoints.remove(new Point3D(x, y, 0));
                grass.setCenter(new Point3D(x, y, 0));
                List<BaseGameComponent> list1 = map.get(0l).get(x).getOrDefault(y, new ArrayList<>());
                list1.add(grass);
                map.get(0l).get(x).put(y, list1);
                grass.setWorldName(world.getName());


                if (RandomUtil.getRandom().nextInt(10) > 7) {
                    PhysicsComponent cobble = new Fire();
                    cobble.setCenter(new Point3D(x, y, 1));
                    emptyPoints.remove(new Point3D(x, y, 1));
                    List<BaseGameComponent> list = map.get(1l).get(x).getOrDefault(y, new ArrayList<>());
                    list.add(cobble);
                    map.get(1l).get(x).put(y, list);
                    cobble.setWorldName(world.getName());
                }
            }
        }




        //补充氧气
        for (Point3D emptyPoint : emptyPoints) {
            Oxygen oxygen = new Oxygen();
            oxygen.setCenter(emptyPoint);
            oxygen.setWorldName(world.getName());
            List<BaseGameComponent> list = map.get(emptyPoint.getZ()).get(emptyPoint.getX()).getOrDefault(emptyPoint.getY(), new ArrayList<>());
            list.add(oxygen);
            map.get(emptyPoint.getZ()).get(emptyPoint.getX()).put(emptyPoint.getY(), list);

        }
        log.info("-----------地图数组-----------");

        String[][][] mapStr = new String[10][10][10];

        for (Map.Entry<Long, Map<Long, Map<Long, List<BaseGameComponent>>>> longMapEntry1 : map.entrySet()) {

            for (Map.Entry<Long, Map<Long, List<BaseGameComponent>>> longMapEntry2 : longMapEntry1.getValue().entrySet()) {
                for (Map.Entry<Long, List<BaseGameComponent>> longListEntry3 : longMapEntry2.getValue().entrySet()) {
                    mapStr[Math.toIntExact(longMapEntry1.getKey())][Math.toIntExact(longMapEntry2.getKey())][Math.toIntExact(longListEntry3.getKey())] = longListEntry3.getValue().stream().map(new Function<BaseGameComponent, String>() {
                        @Override
                        public String apply(BaseGameComponent baseGameComponent) {
                            return baseGameComponent.getName() + "|";
                        }
                    }).collect(Collectors.joining());
                }

            }

        }
        for (int i = 0; i < mapStr.length; i++) {
            log.info("----------------------");

            log.info("z=" + i);

            StringBuilder sb = new StringBuilder();

            for (int y = 0; y < mapStr[i].length; y++) {
                for (int x = 0; x < mapStr[i][y].length; x++) {
                    sb.append(mapStr[i][y][x]).append("   ");
                }
                sb.append("\n");

            }
            log.info(sb.toString());

            log.info("----------------------");

        }
        log.info("-----------地图数组-----------");


        return map;
    }

    @Override
    public Map<Long, Map<Long, Map<Long, WorldNode>>> generateWorldNode(World world) {
        Map<Long, Map<Long, Map<Long, WorldNode>>> worldNodeMap = new HashMap<>();

        for (long z = 0; z < height; z++) {
            worldNodeMap.put(z, new HashMap<>());
            for (long x = 0; x < length; x++) {
                worldNodeMap.get(z).put(x, new HashMap<>());
                for (long y = 0; y < width; y++) {
                    worldNodeMap.get(z).get(x).put(y, new WorldNode(20, 50, 1000));
                }
            }
        }
        return worldNodeMap;
    }
}
