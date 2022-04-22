package com.fengmaster.game.base.obj;

import com.fengmaster.game.base.Game;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * 游戏对象基类
 */
public class BaseGameComponent {

    @Getter
    private String worldName;

    /**
     * ID
     */
    @Getter
    private String uuid;
    /**
     * 名称
     */
    private String name;

    private Map<String, List<BaseGameComponent>> components;

    @Setter
    @Getter
    private BaseGameComponent parentGameObject;



    public BaseGameComponent(){
        uuid=UUID.randomUUID().toString();
        components=new HashMap<>();
        Game.getInstance().getGameObjectCenter().addObject(this);
    }

    public void tick(long tick) {

    }


    public void addComponent(String relationName, BaseGameComponent component){
        List<BaseGameComponent> list = components.getOrDefault(relationName, new ArrayList<BaseGameComponent>());
        list.add(component);
        components.put(relationName,list);
        component.setParentGameObject(this);
    }

    public boolean containsComponent(String relationName){
        return components.containsKey(relationName);
    }


    public List<BaseGameComponent> getComponent(String relationName){
        return components.get(relationName);
    }

    public <T extends BaseGameComponent> List<T> getComponent(String relationName, Class<T> c){
        return (List<T>)components.get(relationName);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
        //默认情况下，会将其他组件都设置成同一个世界ID
        this.components.entrySet().stream().flatMap((Function<Map.Entry<String, List<BaseGameComponent>>, Stream<BaseGameComponent>>) stringListEntry -> stringListEntry.getValue().stream()).forEach(new Consumer<BaseGameComponent>() {
            @Override
            public void accept(BaseGameComponent baseGameComponent) {
                baseGameComponent.setWorldName(worldName);
            }
        });

        if (Game.getInstance().getEventCenter().getWorldEventBus(worldName).isRegistered(this)){
            Game.getInstance().getEventCenter().getWorldEventBus(worldName).unregister(this);
        }
        if (needRegisterWorldListener()){
            Game.getInstance().getEventCenter().getWorldEventBus(worldName).register(this);
        }


    }

    public boolean needRegisterWorldListener(){
        return false;
    }

    public void destroy(){

    }
}
