package com.yuyoubang.otto;

import com.squareup.otto.Bus;

/**
 * Created by xiaoyuan on 16/6/24.
 */
public class AppBus extends Bus{

    private static AppBus bus;

    public static AppBus getInstance() {
        if (bus == null) {
            bus = new AppBus();
        }
        return bus;
    }
}



