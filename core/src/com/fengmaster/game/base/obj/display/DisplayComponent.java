package com.fengmaster.game.base.obj.display;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.fengmaster.game.base.obj.BaseGameComponent;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public abstract class DisplayComponent extends BaseGameComponent {

    protected List<Texture> textures=new ArrayList<>();


    public void addTexture(Texture texture){
        textures.add(texture);
    }

    public abstract Texture getTexture(Object o);



}
