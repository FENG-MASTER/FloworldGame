package com.fengmaster.game.base.obj;

import com.fengmaster.game.base.obj.display.DisplayComponent;
import com.fengmaster.game.base.obj.display.RandomDisplayComponent;

/**
 * 石子
 */
public class Cobble extends BaseGameComponent{

    public Cobble(){
        PhysicsComponent physicsObject = new PhysicsComponent();
        physicsObject.setMass(20);
        physicsObject.setVolume(1);
        addComponent("physics",physicsObject);
        DisplayComponent displayComponent=new RandomDisplayComponent();
        displayComponent.addTexture("cobble1");
        addComponent("texture",displayComponent);
    }
}
