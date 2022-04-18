package com.fengmaster.game.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GameOption {

    public Map mapOption;

    public static class Map{
        public String generatorType;
        public String params;
    }


    public GameOption(){
        mapOption=new Map();
        Preferences map = Gdx.app.getPreferences("FloworldGame");
        mapOption.generatorType=map.getString("map.generatorType");
        mapOption.params=map.getString("map.params");
    }

}
