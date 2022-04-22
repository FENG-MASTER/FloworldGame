package com.fengmaster.game.base.obj.ability;

import com.fengmaster.game.base.obj.PhysicsComponent;
import com.fengmaster.game.base.obj.display.DisplayComponent;
import com.fengmaster.game.base.obj.display.RandomDisplayComponent;

/**
 * 火
 */
public class Fire extends PhysicsComponent {

    public Fire() {
        //小火焰
        this.setName("Fire");
        this.setVolume(0.001);
        DisplayComponent displayComponent=new RandomDisplayComponent();
        displayComponent.addTexture("fire");
        displayComponent.setAlpha(this.getVolume());
        addComponent("texture",displayComponent);
    }

}
