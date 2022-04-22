package com.fengmaster.game.base.obj.entity;

import com.fengmaster.game.base.constant.AttributeKeyEnum;
import com.fengmaster.game.base.obj.PhysicsComponent;
import com.fengmaster.game.base.obj.display.DisplayComponent;
import com.fengmaster.game.base.obj.display.RandomDisplayComponent;

public class Soil extends PhysicsComponent {


    public Soil() {
        this.setName("Soil");
        this.setMass(3000);
        this.setVolume(1);
        DisplayComponent displayComponent=new RandomDisplayComponent();
        displayComponent.addTexture("soil");
        addComponent(AttributeKeyEnum.TEXTURE.name(),displayComponent);
    }
}
