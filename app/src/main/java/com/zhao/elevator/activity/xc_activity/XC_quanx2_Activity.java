package com.zhao.elevator.activity.xc_activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.zhao.elevator.R;

/**
 * Created by zhao on 2016/12/17.
 */

public class XC_quanx2_Activity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xc_cengjin4);
        findViewById(R.id.agree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simple(v);
            }
        });
    }
    public void simple(View v){
        AlertDialog.Builder builder=new AlertDialog.Builder(XC_quanx2_Activity.this)
                .setTitle("请发送验证码")
                .setIcon(R.drawable.grey)
                .setMessage("格式要求：不少于6位字符")
                .setView(new EditText(XC_quanx2_Activity.this))
                .setPositiveButton("确定",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.create().show();
    }
}
