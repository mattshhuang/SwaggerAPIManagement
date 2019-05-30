package com.ustc.apimsservice.bean;

import java.io.Serializable;

/**
 * @Author Matthew Huang
 * @Date 2019-5-23 10:08
 */
public class Service implements Serializable {

    private long id;
    private String name;
    private String type;
    private double time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public Service(long id, String name, String type, double time) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", time=" + time +
                '}';
    }
}
