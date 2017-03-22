package com.tcs.sinanews.utils;

import org.simple.eventbus.EventBus;

/**
 * Created by hzypf on 2017/3/23.
 */

public class EventBusHelper {
    private static EventBus eventBus = EventBus.getDefault();

    public static void register(Object subscriber) {
        eventBus.register(subscriber);
    }

    public static void unregister(Object subscriber) {
        eventBus.unregister(subscriber);
    }

    public static void post(Object target, String tag) {
        eventBus.post(target, tag);
    }
}
