package com.fengmaster.game.base;

import com.fengmaster.game.base.obj.BaseGameComponent;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class GameObjectCenter {

    @Getter
    private  Map<String, BaseGameComponent> uuid2ObjectMap =new HashMap<>();

    private  Map<String,Class> id2ClassMap;


    public void addObject(BaseGameComponent baseGameComponent){
        uuid2ObjectMap.put(baseGameComponent.getUuid(), baseGameComponent);
    }

    public void removeObject(String uuid){
        uuid2ObjectMap.remove(uuid);
    }

}
