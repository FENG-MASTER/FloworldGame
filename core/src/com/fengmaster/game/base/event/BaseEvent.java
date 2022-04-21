package com.fengmaster.game.base.event;

import lombok.Data;

@Data
public abstract class BaseEvent {
    private long time;
    /**
     * 发送者
     */
    private Object sender;

    public BaseEvent(long time, Object sender) {
        this.time=time;
        this.sender=sender;
    }

}
