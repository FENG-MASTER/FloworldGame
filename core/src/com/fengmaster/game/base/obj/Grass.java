package com.fengmaster.game.base.obj;

import com.badlogic.gdx.graphics.Texture;
import com.fengmaster.game.base.obj.display.DisplayComponent;
import com.fengmaster.game.base.obj.display.RandomDisplayComponent;

public class Grass extends BaseGameComponent {

    public Grass(){
        PhysicsComponent physicsObject = new PhysicsComponent();
        physicsObject.setMass(11);
        physicsObject.setVolume(1);
        addComponent("physics",physicsObject);
        DisplayComponent displayComponent=new RandomDisplayComponent();
        displayComponent.addTexture(new Texture("obj/grass1.png"));
        displayComponent.addTexture(new Texture("obj/grass2.png"));
        addComponent("texture",displayComponent);
    }


}
