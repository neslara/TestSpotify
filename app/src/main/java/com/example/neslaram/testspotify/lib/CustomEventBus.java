package com.example.neslaram.testspotify.lib;

/**
 * Created by desarrollo on 7/6/16.
 */
public interface CustomEventBus {
    void register(Object subscriber);

    void unregister(Object subscriber);

    void post(Object subscriber);
}
