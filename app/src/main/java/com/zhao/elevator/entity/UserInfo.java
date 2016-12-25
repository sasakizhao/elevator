package com.zhao.elevator.entity;

/**
 * Created by zhao on 2016/12/8.
 */

public class UserInfo {
    private String username,id,password,commonFloor,startFloor;

    public String getStartFloor() {
        return startFloor;
    }

    public void setStartFloor(String startFloor) {
        this.startFloor = startFloor;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getCommonFloor() {
        return commonFloor;
    }

    public void setCommonFloor(String commonFloor) {
        this.commonFloor = commonFloor;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", commonFloor='" + commonFloor + '\'' +
                ", startFloor='" + startFloor + '\'' +
                '}';
    }
}

