package com.example.neslaram.testspotify.lib;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by desarrollo on 7/6/16.
 */
public class GreenRobotEventBus implements CustomEventBus {
    EventBus eventBus;

    private static class SingletonHolder {
        private static final GreenRobotEventBus INSTANCE = new GreenRobotEventBus();
    }

    public static GreenRobotEventBus getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public GreenRobotEventBus() {
        this.eventBus = org.greenrobot.eventbus.EventBus.getDefault();
    }

    @Override
    public void register(Object subscriber) {
        eventBus.register(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        eventBus.unregister(subscriber);
    }

    @Override
    public void post(Object subscriber) {
        eventBus.post(subscriber);
    }
}
