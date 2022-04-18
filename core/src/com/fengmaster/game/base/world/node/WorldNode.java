package com.fengmaster.game.base.world.node;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 包含温度湿度等信息
 */
@Data
@AllArgsConstructor
public  class WorldNode {

    private double temperature;
    private double humidity;
    private double light;


}
