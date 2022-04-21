package com.fengmaster.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.fengmaster.game.base.Game;
import com.fengmaster.game.base.obj.BaseGameComponent;
import com.fengmaster.game.base.obj.PhysicsComponent;
import com.fengmaster.game.base.obj.display.DisplayComponent;
import com.fengmaster.game.base.texture.TextureCenter;
import com.fengmaster.game.base.world.Point3D;
import com.fengmaster.game.helper.LazyBitmapFont;

import java.util.function.Consumer;

public class FloworldGame extends ApplicationAdapter {

	private Stage stage;

	SpriteBatch batch;

	private Pixmap pixmap;
	OrthographicCamera guiCam;

	FreeTypeFontGenerator gont ;
	LazyBitmapFont lazyBitmapFont ;
	@Override
	public void create () {
		Gdx.graphics.setWindowedMode(480,320);
		Game.getInstance().init();
		batch = new SpriteBatch();
		guiCam=new OrthographicCamera(480,320);
		guiCam.position.set(480 / 2, 320 / 2, 0);

		TextureCenter.addTexture("grass1",new Texture("obj/grass1.png"));
		TextureCenter.addTexture("grass2",new Texture("obj/grass2.png"));

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
//		guiCam.update();
//		batch.setProjectionMatrix(guiCam.combined);
		ScreenUtils.clear(0, 0, 0, 1);

		batch.begin();


		batch.draw(new Texture("obj/grass1.png"), 100,0,10,10);


		Game.getInstance().getGameObjectCenter().getUuid2ObjectMap().values().stream().forEach(new Consumer<BaseGameComponent>() {
			@Override
			public void accept(BaseGameComponent baseGameComponent) {
				if (baseGameComponent instanceof DisplayComponent){
					DisplayComponent displayComponent= (DisplayComponent) baseGameComponent;
					Point3D point3D = baseGameComponent.getParentGameObject().getComponent("physics", PhysicsComponent.class).get(0).getCenter();
					if (new Vector3(point3D.getX()*20,point3D.getY()*20,0).x>0
							&&new Vector3(point3D.getX()*20,point3D.getY()*20,0).x<480
							&&new Vector3(point3D.getX()*20,point3D.getY()*20,0).y<320
					&&new Vector3(point3D.getX()*20,point3D.getY()*20,0).y>0){
//						batch.draw(new Texture(pixmap), point3D.getX()*20, point3D.getY()*20);
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
		stage.dispose();

	}
}
