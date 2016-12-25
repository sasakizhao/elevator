package com.zhao.elevator.activity.xc_activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.zhao.elevator.R;

/**
 * Created by zhao on 2016/12/17.
 */

public class XC_003_Activity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xc_qunti);
        findViewById(R.id.btn_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changStartFloor();
            }
        });
    }

    //修改常用楼层
    void changStartFloor() {
        final String[] items = new String[]{
                "小曹 学生 G909", "阳阳 学生 E1217", "小黄 学生 G0911", "小梁 学生 G0915", "大孟 学生 E1201"};
        //用dialog修改
        AlertDialog.Builder startbuilder = new AlertDialog.Builder(XC_003_Activity.this)
                .setTitle("群体1")
                .setIcon(R.drawable.notification)
                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        // 为AlertDialog.Builder添加“确定”按钮
        setStartbuilderPositiveButton(startbuilder);
        // 为AlertDialog.Builder添加“取消”按钮
        setNegativeButton(startbuilder)
                .create()
                .show();
    }
    private AlertDialog.Builder setStartbuilderPositiveButton(
            AlertDialog.Builder builder) {
        // 调用setPositiveButton方法添加“确定”按钮
        return builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
    }

    private AlertDialog.Builder setNegativeButton(
            AlertDialog.Builder builder) {
        // 调用setNegativeButton方法添加“取消”按钮
        return builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
    }
}
