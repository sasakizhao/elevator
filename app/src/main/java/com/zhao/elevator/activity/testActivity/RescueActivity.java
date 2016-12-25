package com.zhao.elevator.activity.testActivity;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zhao.elevator.R;

/**
 * Created by zhao on 2016/12/9.
 */

public class RescueActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rescue1);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changStartFloor();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RescueActivity.this,"救援信息已发送，请耐心等待",Toast.LENGTH_LONG).show();
            }
        });
    }


    //修改常用楼层
    void changStartFloor() {
        final String[] items = new String[]{
                "注册号：id_1，地点：新主楼C区", "注册号：id_2，地点：新主楼B区", "注册号：id_3，地点：新主楼E区东部", "注册号：id_4，地点：新主楼E区西部", "注册号：id_5，地点：一号楼东部", "注册号：id_6，地点：一号楼西部"};
        //用dialog修改
        AlertDialog.Builder startbuilder = new AlertDialog.Builder(RescueActivity.this)
                .setTitle("请选择被困电梯")
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
