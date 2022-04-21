package com.fengmaster.game.base.obj.display;

import cn.hutool.core.util.RandomUtil;
import com.badlogic.gdx.graphics.Texture;

public class RandomDisplayComponent  extends DisplayComponent{
    @Override
    public String getTexture(Object o) {
        return textures.get(RandomUtil.randomInt(textures.size()));
    }
}
