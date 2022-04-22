package com.fengmaster.game.base.obj.ability;

import cn.hutool.core.collection.CollectionUtil;
import com.fengmaster.game.base.Game;
import com.fengmaster.game.base.constant.AttributeKeyEnum;
import com.fengmaster.game.base.event.TickEvent;
import com.fengmaster.game.base.obj.BaseGameComponent;
import com.fengmaster.game.base.obj.PhysicsComponent;
import com.fengmaster.game.base.obj.display.DisplayComponent;
import com.fengmaster.game.base.obj.display.RandomDisplayComponent;
import com.fengmaster.game.base.obj.fluid.Oxygen;
import com.fengmaster.game.base.world.node.WorldNode;
import lombok.Getter;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 火
 */
public class Fire extends PhysicsComponent {

    /**
     * 火势情况
     */
    @Getter
    private double spread;

    public Fire() {
        //小火焰
        this.setName("Fire");
        this.setVolume(0.001);
        DisplayComponent displayComponent=new RandomDisplayComponent();
        displayComponent.addTexture("fire");
        displayComponent.setAlpha(this.getVolume());
        addComponent(AttributeKeyEnum.TEXTURE.name(),displayComponent);
        Game.getInstance().getEventCenter().getWorldEventBus(getWorldName()).register(this);
    }

    public void setSpread(double spread){
        this.spread=spread;
        this.setVolume(spread);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void tick(TickEvent tickEvent){
        if (spread>=1){
            //开始扩撒,向四周八个格子扩散，生成小火种

        }else {
            //检查是否可以增大火势
            List<BaseGameComponent> centerCellObjs = Game.getInstance().getWorld(getWorldName()).getWorldObject(getCenter());
            //氧气
            List<Oxygen> oxygens = centerCellObjs.stream().filter(baseGameComponent -> baseGameComponent instanceof Oxygen)
                    .map(baseGameComponent -> (Oxygen) baseGameComponent).collect(Collectors.toList());

            WorldNode worldNode = Game.getInstance().getWorld(getWorldName()).getWorldNode(this.getCenter());
            for (BaseGameComponent gameComponent : centerCellObjs) {
                if (gameComponent.containsComponent(AttributeKeyEnum.COMBUSTIBLE.name())){
                    //物品属于可燃物
                    Combustible combustible = gameComponent.getComponent(AttributeKeyEnum.COMBUSTIBLE.name(), Combustible.class).get(0);
                    if(combustible.isBurning() ){
                            //可燃物，并且已经着了
                        if (CollectionUtil.isNotEmpty(oxygens)||oxygens.stream().map(new Func).count()<=0){
                            //一点氧气都没用了
                            combustible.unBurn();
                        }

                    }else if(combustible.getBurnPoint()<worldNode.getTemperature()) {
                            //没有点着，但是温度已经达到燃点

                    }

                }
            }

        }

    }

    @Override
    public boolean needRegisterWorldListener() {
        return true;
    }
}
