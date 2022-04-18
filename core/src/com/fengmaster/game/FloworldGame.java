package com.fengmaster.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.fengmaster.game.base.Game;
import com.fengmaster.game.base.obj.BaseGameComponent;
import com.fengmaster.game.base.obj.PhysicsComponent;
import com.fengmaster.game.base.obj.display.DisplayComponent;
import com.fengmaster.game.base.world.Point3D;

import java.util.function.Consumer;

public class FloworldGame extends ApplicationAdapter {

	private Stage stage;

	SpriteBatch batch;


	@Override
	public void create () {
		Gdx.graphics.setWindowedMode(480,360);
		Game.getInstance().init();
		batch = new SpriteBatch();
		stage=new Stage(new ScalingViewport(Scaling.fit,2048,1080),batch);
//		for (Map.Entry<Integer, Map<Integer, Map<Integer, BaseGameComponent>>> mapEntryX : Game.getInstance().getWorld().getGameObjectMap().entrySet()) {
//			Integer x = mapEntryX.getKey();
//			for (Map.Entry<Integer, Map<Integer, BaseGameComponent>> mapEntryY : mapEntryX.getValue().entrySet()) {
//				Integer y = mapEntryY.getKey();
//				for (Map.Entry<Integer, BaseGameComponent> mapEntryZ : mapEntryY.getValue().entrySet()) {
//					Integer z = mapEntryZ.getKey();
//					BaseGameComponent component = mapEntryZ.getValue();
//
//
//				}
//
//			}
//		}

//
//		Map map=new Map();
//		map.getLayers().get(0).getObjects().get(0).getProperties();
//		TiledMap tiledMap=new TiledMap();
//		Pixmap pixmap;
//		pixmap.drawPixel();

	}

	@Override
	public void render () {

		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		Pixmap pixmap=new Pixmap(10,10, Pixmap.Format.Alpha);
		pixmap.drawCircle(5,5,5);
		pixmap.setColor(new Color(1,0,0,1));
		Game.getInstance().getGameObjectCenter().getUuid2ObjectMap().values().stream().forEach(new Consumer<BaseGameComponent>() {
			@Override
			public void accept(BaseGameComponent baseGameComponent) {
				if (baseGameComponent instanceof DisplayComponent){
					DisplayComponent displayComponent= (DisplayComponent) baseGameComponent;
					Point3D point3D = baseGameComponent.getParentGameObject().getComponent("physics", PhysicsComponent.class).get(0).getCenter();
					batch.draw(new Texture(pixmap), point3D.getX()*20, point3D.getY()*20);

				}
			}
		});

		batch.end();
		stage.act();
		stage.draw();
	}

	@Override
	public void dispose () {
//		batch.dispose();
		stage.dispose();

	}
}
