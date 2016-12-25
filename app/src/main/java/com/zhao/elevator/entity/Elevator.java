package com.zhao.elevator.entity;

import java.util.Arrays;

/**
 * @ClassName: Elevator
 * @Description: TODO(电梯类，描述电梯属性)
 * @author SasakiZhao
 * @date 2016年3月16日 上午9:32:56
 *  id 	电梯id
 *  position	电梯所处楼层
 *  action	电梯正在执行的动作（包括上升，下降，停止）
 *  currentWeight	电梯已容纳重量
 *  weight	电梯最大容纳重量
 *  dockFloor	电梯将会停靠的楼层
 *
 */

public class Elevator {
    private String id;
    private String position;
    private String action;
    private String currentWeight;
    private String weight;
    private String[] dockFloor;
    // private String dockFloor;
    private String time;

    public Elevator() {
    }

    public Elevator(String id, String position, String action, String currentWeight, String weight, String[] dockFloor, String time) {
        this.id = id;
        this.position = position;
        this.action = action;
        this.currentWeight = currentWeight;
        this.weight = weight;
        this.dockFloor = dockFloor;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(String currentWeight) {
        this.currentWeight = currentWeight;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String[] getDockFloor() {
        return dockFloor;
    }

    public void setDockFloor(String[] dockFloor) {
        this.dockFloor = dockFloor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "id='" + id + '\'' +
                ", position='" + position + '\'' +
                ", action='" + action + '\'' +
                ", currentWeight='" + currentWeight + '\'' +
                ", weight='" + weight + '\'' +
                ", dockFloor=" + Arrays.toString(dockFloor) +
                ", time='" + time + '\'' +
                '}';
    }

}

