package com.fengmaster.game.base;

import com.badlogic.gdx.Gdx;
import com.fengmaster.game.base.world.World;
import com.fengmaster.game.base.world.gen.PureWorldGenerator;
import com.fengmaster.game.base.event.EventCenter;
import lombok.Getter;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Game {

    private static  Game instance=new Game();

    public static Game getInstance() {
        return instance;
    }

    @Getter
    private GameOption gameOption;

    private Map<String,World> worldMap;

    @Getter
    private GameObjectCenter gameObjectCenter;

    @Getter
    private EventCenter eventCenter;


    private Game(){
        //TODO:
        Gdx.app.getPreferences("FloworldGame").putString("map.generatorType", PureWorldGenerator.class.getName());
        Gdx.app.getPreferences("FloworldGame").putString("map.params","");

    }

    public void init(){
        gameOption =new GameOption();
        eventCenter=new EventCenter();
        worldMap=new ConcurrentHashMap<>();

        gameObjectCenter=new GameObjectCenter();

        World world=new World("main",new PureWorldGenerator());
        worldMap.put(world.getName(),world);
    }

    public World getWorld(String name){
        return worldMap.get(name);
    }

    public Collection<String> getWorldNames(){
        return worldMap.keySet();
    }

}
