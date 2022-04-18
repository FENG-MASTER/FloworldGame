package com.fengmaster.game.base;

import com.badlogic.gdx.Gdx;
import com.fengmaster.game.base.world.World;
import com.fengmaster.game.base.world.gen.PureWorldGenerator;
import lombok.Getter;

public class Game {

    private static  Game instance=new Game();

    public static Game getInstance() {
        return instance;
    }

    @Getter
    private GameOption gameOption;

    @Getter
    private World world;

    @Getter
    private GameObjectCenter gameObjectCenter;


    private Game(){
        //TODO:
        Gdx.app.getPreferences("FloworldGame").putString("map.generatorType", PureWorldGenerator.class.getName());
        Gdx.app.getPreferences("FloworldGame").putString("map.params","");



    }

    public void init(){
        gameOption =new GameOption();
        gameObjectCenter=new GameObjectCenter();
        world=new World(new PureWorldGenerator());

    }

}
