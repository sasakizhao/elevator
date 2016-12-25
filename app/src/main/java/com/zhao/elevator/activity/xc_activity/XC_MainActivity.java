package com.zhao.elevator.activity.xc_activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zhao.elevator.R;
import com.zhao.elevator.activity.MainActivity;

/**
 * Created by zhao on 2016/12/17.
 */

public class XC_MainActivity  extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xc_activity_main);

        findViewById(R.id.xc_zc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(XC_MainActivity.this, XC_Reg2Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.xc_zhuce2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(XC_MainActivity.this, XC_LoginActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.xc_denglu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(XC_MainActivity.this, XC_LoginActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.xc_zhujiemian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(XC_MainActivity.this, XC_indexActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.xc_zuikuai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(XC_MainActivity.this, XC_fast_elevator_Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.xc_gudingzuikuai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(XC_MainActivity.this, XC_fast2_Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.xc_yunxing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(XC_MainActivity.this, XC_yunxing_Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.xc_dinghsi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(XC_MainActivity.this, XC_dingshi_Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.xc_anquan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(XC_MainActivity.this, XC_anquan_Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.anquan_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(XC_MainActivity.this, XC_anquan_set_Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.xc_qunti).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(XC_MainActivity.this, XC_qunti_Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(XC_MainActivity.this, XC_cengjin_Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button20).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(XC_MainActivity.this, XC_cengjin2_Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(XC_MainActivity.this, XC_quanx1_Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(XC_MainActivity.this, XC_quanx2_Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button26).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(XC_MainActivity.this, XC_001_Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button27).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(XC_MainActivity.this, XC_002_Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button28).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(XC_MainActivity.this, XC_xinxiguanli1.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button29).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(XC_MainActivity.this, XC_xinxiguanli2.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button30).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(XC_MainActivity.this, XC_map_Activity.class);
                startActivity(intent);
            }
        });
    }
}
