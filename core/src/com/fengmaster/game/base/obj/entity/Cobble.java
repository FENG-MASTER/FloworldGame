package com.fengmaster.game.base.obj.entity;

import com.fengmaster.game.base.constant.AttributeKeyEnum;
import com.fengmaster.game.base.obj.BaseGameComponent;
import com.fengmaster.game.base.obj.PhysicsComponent;
import com.fengmaster.game.base.obj.display.DisplayComponent;
import com.fengmaster.game.base.obj.display.RandomDisplayComponent;

/**
 * 石子
 */
public class Cobble extends PhysicsComponent {

    public Cobble(){
        this.setName("Cobble");
        this.setMass(3000);
        this.setVolume(1);
        DisplayComponent displayComponent=new RandomDisplayComponent();
        displayComponent.addTexture("cobble1");
        addComponent(AttributeKeyEnum.TEXTURE.name(),displayComponent);
    }
}
