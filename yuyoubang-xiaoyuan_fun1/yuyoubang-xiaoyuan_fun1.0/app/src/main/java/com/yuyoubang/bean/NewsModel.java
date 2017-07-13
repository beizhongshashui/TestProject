package com.yuyoubang.bean;

import java.io.Serializable;

/**
 * Created by wangchang on 2016/3/31.
 */
public class NewsModel implements Serializable {
    public String item="";

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
