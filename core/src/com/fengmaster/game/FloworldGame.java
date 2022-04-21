package com.fengmaster.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.fengmaster.game.base.Game;
import com.fengmaster.game.base.obj.BaseGameComponent;
import com.fengmaster.game.base.obj.PhysicsComponent;
import com.fengmaster.game.base.obj.display.DisplayComponent;
import com.fengmaster.game.base.texture.TextureCenter;
import com.fengmaster.game.base.world.Point3D;

import java.util.function.Consumer;

public class FloworldGame extends ApplicationAdapter {


	SpriteBatch batch;

	OrthographicCamera guiCam;

	private long currentShowTick;

	private boolean nextTick;

	private String worldName;

	@Override
	public void create () {
		Gdx.graphics.setWindowedMode(480,320);
		Game.getInstance().init();
		batch = new SpriteBatch();
		guiCam=new OrthographicCamera(480,320);
		guiCam.position.set(480 / 2, 320 / 2, 0);

		TextureCenter.addTexture("grass1",new Texture("obj/grass1.png"));
		TextureCenter.addTexture("grass2",new Texture("obj/grass2.png"));
		worldName="main";



	}

	@Override
	public void render () {
		guiCam.update();
		batch.setProjectionMatrix(guiCam.combined);
		ScreenUtils.clear(0, 0, 0, 1);

		batch.begin();


		batch.draw(new Texture("obj/grass1.png"), 100,0,10,10);


		Game.getInstance().getGameObjectCenter().getUuid2ObjectMap().values().stream().forEach(new Consumer<BaseGameComponent>() {
			@Override
			public void accept(BaseGameComponent baseGameComponent) {
				if (baseGameComponent instanceof DisplayComponent&&baseGameComponent.getWorldName().equals(worldName)){
					DisplayComponent displayComponent= (DisplayComponent) baseGameComponent;
					Point3D point3D = baseGameComponent.getParentGameObject().getComponent("physics", PhysicsComponent.class).get(0).getCenter();
					if (guiCam.unproject(new Vector3(point3D.getX()*20,point3D.getY()*20,0)).x>0
							&&guiCam.unproject(new Vector3(point3D.getX()*20,point3D.getY()*20,0)).x<480
							&&guiCam.unproject(new Vector3(point3D.getX()*20,point3D.getY()*20,0)).y<320
					&&guiCam.unproject(new Vector3(point3D.getX()*20,point3D.getY()*20,0)).y>0){
						batch.draw(TextureCenter.getTexture(displayComponent.getTexture(null)), point3D.getX()*20,point3D.getY()*20,20,20);

					}

				}
			}
		});

		batch.end();

	}

	@Override
	public void dispose () {
		batch.dispose();

	}
}
