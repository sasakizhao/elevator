package com.zhao.elevator.utils;

import android.app.Application;
import android.content.SharedPreferences;
import com.zhao.elevator.entity.Elevator;
import com.zhao.elevator.entity.UserInfo;
import java.util.List;

/**
 * Created by zhao on 2016/12/8.
 */

public class Data extends Application {
    public static final String PORT = "8080";
    private List<Elevator> elevatorList;
    private UserInfo ui;
    private String ipAddress;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public SharedPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public SharedPreferences.Editor getEditor() {
        return editor;
    }

    public void setEditor(SharedPreferences.Editor editor) {
        this.editor = editor;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public List<Elevator> getElevatorList() {
        return elevatorList;
    }

    public void setElevatorList(List<Elevator> elevatorList) {
        this.elevatorList = elevatorList;
    }

    public UserInfo getUi() {
        return ui;
    }

    public void setUi(UserInfo ui) {
        this.ui = ui;
    }
}
