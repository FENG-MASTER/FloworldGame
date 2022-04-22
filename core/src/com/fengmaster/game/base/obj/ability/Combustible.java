package com.fengmaster.game.base.obj.ability;

import com.fengmaster.game.base.Game;
import com.fengmaster.game.base.event.TickEvent;
import com.fengmaster.game.base.obj.BaseGameComponent;
import lombok.Getter;
import lombok.Setter;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 可燃的
 */
public class Combustible extends BaseGameComponent {

    /**
     * 燃点
     */
    @Getter
    private double burnPoint;
    /**
     * 可燃烧时间
     */
    private double burnTime;

    /**
     * 剩余可燃烧时间
     */
    private double remainingBurnTime;

    /**
     * 是否已经点燃
     */
    @Getter
    private boolean isBurning;

    /**
     * 上次燃烧时间
     */
    private long lastBurnTime;


    public Combustible(double burnPoint,double burnTime){
        this.burnPoint=burnPoint;
        this.burnTime=burnTime;
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void tick(TickEvent tickEvent){
        if (isBurning){
            long burnTime = tickEvent.getTime() - lastBurnTime;
            remainingBurnTime-=burnTime;
            if(remainingBurnTime<0){
                //烧完了，游戏物体销毁
                getParentGameObject().destroy();
            }
        }
    }


    /**
     * 燃烧
     */
    public void burn(){
        lastBurnTime = Game.getInstance().getWorld(getWorldName()).getTimeCenter().getTime();
        isBurning=true;
    }


    public void unBurn(){
        lastBurnTime=0;
        isBurning=false;
    }


    @Override
    public boolean needRegisterWorldListener() {
        return true;
    }
}
