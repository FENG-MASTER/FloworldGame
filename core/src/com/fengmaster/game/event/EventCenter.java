package com.fengmaster.game.event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventCenter {

    private  Map<String, EventBus> worldBusMap = new ConcurrentHashMap<>();

    public EventCenter(){
        EventBus.getDefault().register(this);
    }

    public  EventBus getWorldEventBus(String wUUID) {
        return worldBusMap.get(wUUID);
    }


    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void worldCreated(WorldCreatedEvent worldCreatedEvent) {
        if(!worldBusMap.containsKey(worldCreatedEvent.getWorld().getName())){
            worldBusMap.put(worldCreatedEvent.getWorld().getName(),EventBus.builder().logNoSubscriberMessages(false).build());

        }
    }
}
