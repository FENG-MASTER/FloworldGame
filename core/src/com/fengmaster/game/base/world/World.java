package com.fengmaster.game.base.world;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.fengmaster.game.base.obj.BaseGameComponent;
import com.fengmaster.game.base.world.gen.BaseWorldGenerator;
import com.fengmaster.game.base.world.node.WorldNode;
import lombok.Getter;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 世界对象
 */
@Log
public class World {

    private String uuid= UUID.randomUUID().toString();

    private long length=50;
    private long width=50;
    private long height=50;

    /**
     * x,y,z地图节点
     */
    private Map<Integer,Map<Integer,Map<Integer, WorldNode>>> worldNodeMap =new HashMap<>();

    @Getter
    private Map<Integer,Map<Integer,Map<Integer, BaseGameComponent>>> gameObjectMap =new HashMap<>();


    public World(BaseWorldGenerator worldGenerator){
        TimeInterval timer = DateUtil.timer();
        log.info("开始生成地图");
        timer.start();
        worldNodeMap=worldGenerator.generateWorldNode(this);
        gameObjectMap=worldGenerator.generateObj(this);

        log.info("生成地图完毕，耗时"+timer.intervalSecond()+"秒");



    }

}
