package com.yuyoubang.bean;

import java.io.Serializable;

/**
 * Created by xiaoyuan on 16/12/8.
 */
public class RegionModel implements Serializable{
    private Long id;
    private Long pid;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
