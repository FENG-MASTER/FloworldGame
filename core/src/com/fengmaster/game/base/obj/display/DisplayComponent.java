package com.fengmaster.game.base.obj.display;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.fengmaster.game.base.obj.BaseGameComponent;
import java.util.ArrayList;
import java.util.List;


public abstract class DisplayComponent extends BaseGameComponent {

    protected List<String> textures=new ArrayList<>();

    public void addTexture(String texture){
        textures.add(texture);
    }

    public abstract String getTexture(Object o);



}
