package com.ustc.apimsservice.bean;

import java.io.Serializable;

/**
 * @author matthew huang
 * @description
 * @date 2019/5/22 3:50 PM
 */
public class User implements Serializable {
    private long id;
    private String name;
    private int age;
    private String accessToken;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public User(long id, String name, int age, String accessToken) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }
}
