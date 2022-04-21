package com.fengmaster.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.fengmaster.game.base.Game;
import com.fengmaster.game.base.obj.BaseGameComponent;
import com.fengmaster.game.base.obj.PhysicsComponent;
import com.fengmaster.game.base.obj.display.DisplayComponent;
import com.fengmaster.game.base.texture.TextureCenter;
import com.fengmaster.game.base.world.Point3D;
import com.fengmaster.game.event.TickEvent;
import lombok.extern.java.Log;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.function.Consumer;

@Log
public class FloworldGame extends ApplicationAdapter {


	SpriteBatch batch;

	OrthographicCamera guiCam;

	private long currentShowTick;

	private boolean nextTick;

	private String worldName;

	private int currentZ=0;

	private int commandWaitTime=5;


	@Override
	public void create () {
		Gdx.graphics.setWindowedMode(480,320);
		Game.getInstance().init();
		batch = new SpriteBatch();
		guiCam=new OrthographicCamera(480,320);
		guiCam.position.set(480 / 2, 320 / 2, 0);

		TextureCenter.addTexture("grass1",new Texture("obj/grass1.png"));
		TextureCenter.addTexture("grass2",new Texture("obj/grass2.png"));
		TextureCenter.addTexture("cobble2",new Texture("obj/cobble2.png"));
		TextureCenter.addTexture("cobble1",new Texture("obj/cobble1.png"));
		worldName="main";
		Game.getInstance().getEventCenter().getWorldEventBus(worldName).register(this);


	}



	@Override
	public void render () {

		guiCam.update();
		batch.setProjectionMatrix(guiCam.combined);
		batch.begin();

		if(commandWaitTime>0){
			commandWaitTime--;
		}else {
			if (Gdx.input.isKeyPressed(Input.Keys.N)){
				log.info("按下键盘N");
				Game.getInstance().getWorld(worldName).tick();
			}

			if (Gdx.input.isKeyPressed(Input.Keys.COMMA)&&Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)){
				log.info("按下键盘<");
				currentZ--;
				log.info("当前层数"+currentZ);

				nextTick=true;
			}

			if (Gdx.input.isKeyPressed(Input.Keys.PERIOD)&&Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)){
				log.info("按下键盘>");
				currentZ++;
				log.info("当前层数"+currentZ);
				nextTick=true;

			}

			commandWaitTime=5;
		}

		if (currentZ<0){
			currentZ=0;
		}else if (currentZ>50){
			currentZ=50;
		}

if (nextTick){
	ScreenUtils.clear(0, 0, 0, 1);
	Game.getInstance().getGameObjectCenter().getUuid2ObjectMap().values().stream().forEach(new Consumer<BaseGameComponent>() {
		@Override
		public void accept(BaseGameComponent baseGameComponent) {
			if (baseGameComponent instanceof DisplayComponent&&baseGameComponent.getWorldName().equals(worldName)){
				DisplayComponent displayComponent= (DisplayComponent) baseGameComponent;
				Point3D point3D = baseGameComponent.getParentGameObject().getComponent("physics", PhysicsComponent.class).get(0).getCenter();
				if (point3D.getZ()>currentZ){
					return;
				}
				if (guiCam.unproject(new Vector3(point3D.getX()*20,point3D.getY()*20,0)).x>0
						&&guiCam.unproject(new Vector3(point3D.getX()*20,point3D.getY()*20,0)).x<480
						&&guiCam.unproject(new Vector3(point3D.getX()*20,point3D.getY()*20,0)).y<320
						&&guiCam.unproject(new Vector3(point3D.getX()*20,point3D.getY()*20,0)).y>0){
					//越靠前当前层，越清楚


					batch.setColor(1,1,1,1-((float)currentZ-point3D.getZ())/5);

					batch.draw(TextureCenter.getTexture(displayComponent.getTexture(null)), point3D.getX()*20,point3D.getY()*20,20,20);
				}

			}
		}
	});

	nextTick=false;

}


		batch.end();

	}

	@Override
	public void dispose () {
		batch.dispose();

	}

	@Subscribe(threadMode = ThreadMode.ASYNC)
	public void tick(TickEvent tickEvent){
		if (currentShowTick!=tickEvent.getTime()){
			nextTick=true;
		}
		currentShowTick=tickEvent.getTime();

	}
}
