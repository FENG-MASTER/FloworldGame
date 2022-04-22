package com.fengmaster.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.fengmaster.game.base.Game;
import com.fengmaster.game.base.constant.AttributeKeyEnum;
import com.fengmaster.game.base.obj.BaseGameComponent;
import com.fengmaster.game.base.obj.PhysicsComponent;
import com.fengmaster.game.base.obj.display.DisplayComponent;
import com.fengmaster.game.texture.TextureCenter;
import com.fengmaster.game.base.world.Point3D;
import com.fengmaster.game.base.event.TickEvent;
import lombok.extern.java.Log;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Log
public class FloworldGame extends ApplicationAdapter {


    SpriteBatch batch;

    OrthographicCamera guiCam;

    private long currentShowTick;

    private boolean nextTick;

    private String worldName;

    private int currentZ = 0;

    private int commandWaitTime = 5;


    @Override
    public void create() {
        Gdx.graphics.setWindowedMode(480, 320);
        Game.getInstance().init();
        batch = new SpriteBatch();
        guiCam = new OrthographicCamera(480, 320);
        guiCam.position.set(480 / 2, 320 / 2, 0);
        TextureCenter.loadFromSource();

        worldName = "main";
        Game.getInstance().getEventCenter().getWorldEventBus(worldName).register(this);


    }


    @Override
    public void render() {

        guiCam.update();
        batch.setProjectionMatrix(guiCam.combined);
        batch.begin();

        if (commandWaitTime > 0) {
            commandWaitTime--;
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.N)) {
                log.info("按下键盘N");
                Game.getInstance().getWorld(worldName).tick();
            }

            if (Gdx.input.isKeyPressed(Input.Keys.COMMA) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                log.info("按下键盘<");
                currentZ--;
                log.info("当前层数" + currentZ);

                nextTick = true;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.PERIOD) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                log.info("按下键盘>");
                currentZ++;
                log.info("当前层数" + currentZ);
                nextTick = true;

            }

            commandWaitTime = 5;
        }

        if (currentZ < 0) {
            currentZ = 0;
        } else if (currentZ > 50) {
            currentZ = 50;
        }
        nextTick=true;
        if (nextTick) {
            ScreenUtils.clear(0, 0, 0, 1);

            for (Map.Entry<Long, Map<Long, Map<Long, List<BaseGameComponent>>>> entryZ : Game.getInstance().getWorld(worldName).getGameObjectMap().entrySet()) {
                if (entryZ.getKey() > currentZ) {
                    continue;
                }

                Long z = entryZ.getKey();
                for (Map.Entry<Long, Map<Long, List<BaseGameComponent>>> entryX : entryZ.getValue().entrySet()) {
                    Long x = entryX.getKey();
                    for (Map.Entry<Long, List<BaseGameComponent>> entryY : entryX.getValue().entrySet()) {
                        Long y = entryY.getKey();

                        for (BaseGameComponent gameComponent : entryY.getValue()) {
                            if (!gameComponent.containsComponent(AttributeKeyEnum.TEXTURE.name()) || !gameComponent.getWorldName().equals(worldName)){
                                continue;
                            }

                            DisplayComponent displayComponent = gameComponent.getComponent(AttributeKeyEnum.TEXTURE.name(), DisplayComponent.class).get(0);

                            if ( !(gameComponent instanceof PhysicsComponent)){
                                continue;
                            }
                            Point3D point3D = ((PhysicsComponent)(gameComponent)).getCenter();

                        if (guiCam.unproject(new Vector3(point3D.getX() * 20, point3D.getY() * 20, 0)).x > 0
                                && guiCam.unproject(new Vector3(point3D.getX() * 20, point3D.getY() * 20, 0)).x < 480
                                && guiCam.unproject(new Vector3(point3D.getX() * 20, point3D.getY() * 20, 0)).y < 320
                                && guiCam.unproject(new Vector3(point3D.getX() * 20, point3D.getY() * 20, 0)).y > 0) {
//                                越靠前当前层，越清楚


                                batch.setColor(batch.getColor().r, batch.getColor().g, batch.getColor().b, 1 - ((float) currentZ - point3D.getZ()) / 5);
                                batch.draw(TextureCenter.getTexture(displayComponent.getTexture(null)), point3D.getX() * 20, point3D.getY() * 20, 20, 20);
                        }


                        }

                    }


                }


            }



            nextTick = false;

        }


        batch.end();

    }

    @Override
    public void dispose() {
        batch.dispose();

    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void tick(TickEvent tickEvent) {
        if (currentShowTick != tickEvent.getTime()) {
            nextTick = true;
        }
        currentShowTick = tickEvent.getTime();

    }
}
