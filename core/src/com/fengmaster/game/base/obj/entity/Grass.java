package com.fengmaster.game.base.obj.entity;

import com.badlogic.gdx.graphics.Texture;
import com.fengmaster.game.base.obj.BaseGameComponent;
import com.fengmaster.game.base.obj.PhysicsComponent;
import com.fengmaster.game.base.obj.display.DisplayComponent;
import com.fengmaster.game.base.obj.display.RandomDisplayComponent;

public class Grass extends BaseGameComponent {

    public Grass(){
        PhysicsComponent physicsObject = new PhysicsComponent();
        physicsObject.setMass(11);
        physicsObject.setVolume(1);
        addComponent("physics",physicsObject);
        DisplayComponent displayComponent=new RandomDisplayComponent();
        displayComponent.addTexture("grass1");
        displayComponent.addTexture("grass2");
        addComponent("texture",displayComponent);
    }


}
