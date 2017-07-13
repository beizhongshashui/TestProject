package com.yuyoubang.bean;

import java.io.Serializable;

/**
 * Created by xiaoyuan on 16/11/10.
 */
public class PopBean implements Serializable {
    private String content;
    private int selector;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSelector() {
        return selector;
    }

    public void setSelector(int selector) {
        this.selector = selector;
    }
}
