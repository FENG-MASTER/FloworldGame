package com.fengmaster.game;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeCenter {

    /**
     * 从0开始，每1单位表示游戏中的1秒钟
     */
    private long time;

    /**
     * 游戏开始年份
     */
    private LocalDateTime startTime;


    /**
     * 游戏开始，起始时间格式 2011-12-03T10:15:30
     * @param dateTime
     */
    public void start(String dateTime){
        startTime=LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * 获取当前日期
     * @return
     */
    public LocalDateTime now(){
        return startTime.plusSeconds(time);
    }

    public void next(int step){
        time+=step;
    }
    public void next(){
        time++;
    }
}
