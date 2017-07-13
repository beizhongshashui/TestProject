package com.yuyoubang.otto;

import java.io.Serializable;

/**
 * Created by xiaoyuan on 16/12/12.
 */
public class HomeEvent implements Serializable{
    private static HomeEvent instance;

    private HomeEvent() {
    }

    public static HomeEvent getInstance() {
        if (instance == null) {
            instance = new HomeEvent();
        }
        return instance;
    }

    public String city;
    public String max_price;
    public String min_price;
    public String start_timestamp;
    public String day_min;
    public String day_max;
}
