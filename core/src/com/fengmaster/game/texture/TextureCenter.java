package com.fengmaster.game.texture;

import com.badlogic.gdx.graphics.Texture;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 纹理管理器
 * @author fengwu
 */
public class TextureCenter {
    private static Map<String, Texture> textureMap=new ConcurrentHashMap<>();

    public static void addTexture(String name, Texture texture){
        textureMap.put(name,texture);
    }

    public static Texture getTexture(String name){
        return textureMap.get(name);
    }


}
