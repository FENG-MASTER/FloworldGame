package com.fengmaster.game.texture;

import cn.hutool.core.io.FileUtil;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
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

    public static void loadFromSource(){
        FileHandle[] textureFile = Gdx.files.internal("obj/").list("png");
        for (FileHandle fileHandle : textureFile) {
            addTexture(FileUtil.mainName(fileHandle.name()), new Texture(fileHandle));
        }
    }


}
