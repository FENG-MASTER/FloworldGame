package com.fengmaster.game.base.obj.entity;

import cn.hutool.core.util.RandomUtil;
import com.badlogic.gdx.graphics.Texture;
import com.fengmaster.game.base.constant.AttributeKeyEnum;
import com.fengmaster.game.base.obj.BaseGameComponent;
import com.fengmaster.game.base.obj.PhysicsComponent;
import com.fengmaster.game.base.obj.display.DisplayComponent;
import com.fengmaster.game.base.obj.display.RandomDisplayComponent;

public class Grass extends PhysicsComponent {

    public Grass(){
        this.setName("Grass");
        this.setMass(3000);
        this.setVolume(1);
        DisplayComponent displayComponent=new RandomDisplayComponent();
        if (RandomUtil.getRandom().nextBoolean()){
            displayComponent.addTexture("grass1");
        }else {
            displayComponent.addTexture("grass2");

        }
        addComponent(AttributeKeyEnum.TEXTURE.name(),displayComponent);
    }


}
