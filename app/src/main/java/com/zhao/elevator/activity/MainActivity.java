package com.zhao.elevator.activity;

import android.app.NotificationManager;
import android.app.*;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zhao.elevator.R;
import com.zhao.elevator.activity.testActivity.ElevatorInfoByExpandableListViewActivity;
import com.zhao.elevator.activity.testActivity.FasteleActivity;
import com.zhao.elevator.activity.testActivity.Guzhang1;
import com.zhao.elevator.activity.testActivity.M2Act;
import com.zhao.elevator.activity.testActivity.MapActivity;
import com.zhao.elevator.activity.testActivity.RealtimeActivity;
import com.zhao.elevator.activity.testActivity.RescueActivity;
import com.zhao.elevator.activity.testActivity.SearchActivity;
import com.zhao.elevator.activity.testActivity.WeixiuActivity;
import com.zhao.elevator.activity.xc_activity.XC_LoginActivity;
import com.zhao.elevator.activity.xc_activity.XC_MainActivity;

public class MainActivity extends AppCompatActivity {
    public static final int TYPE_Normal = 1;
    public static final int TYPE_Progress = 2;
    public static final int TYPE_BigText = 3;
    public static final int TYPE_Inbox = 4;
    public static final int TYPE_BigPicture = 5;
    public static final int TYPE_Hangup = 6;
    public static final int TYPE_Media = 7;
    public static final int TYPE_Customer = 8;
    private NotificationManager manger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        setContentView(R.layout.activity_main);
        Button regbtn= (Button) findViewById(R.id.chuce);
        regbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        Button loginbtn= (Button) findViewById(R.id.denglu);
        loginbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        Button main= (Button) findViewById(R.id.zhujiemian);
        main.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,M2Act.class);
                startActivity(intent);

            }
        });
        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,FasteleActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.zuikuai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ElevatorInfoByExpandableListViewActivity.class);
                startActivity(intent);
                simpleNotify();
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RescueActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guzhangNotify();
                Intent intent=new Intent(MainActivity.this,RealtimeActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guzhangNotify();
                Intent intent=new Intent(MainActivity.this,WeixiuActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,Guzhang1.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.button13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this, XC_MainActivity.class);
                startActivity(intent);
            }
        });

    }









    private void simpleNotify(){
        //为了版本兼容  选择V7包下的NotificationCompat进行构造
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        //Ticker是状态栏显示的提示"简单Notification"
        builder.setTicker("您定时乘梯的时间到了");
        //第一行内容  通常作为通知栏标题"请乘坐id_3号电梯"
        builder.setContentTitle("您定时乘梯的时间到了");
        //第二行内容 通常是通知正文
        builder.setContentText("");
        //第三行内容 通常是内容摘要什么的 在低版本机器上不一定显示
        builder.setSubText("   ");
        //ContentInfo 在通知的右侧 时间的下面 用来展示一些其他信息
        //builder.setContentInfo("2");
        //number设计用来显示同种通知的数量和ContentInfo的位置一样，如果设置了ContentInfo则number会被隐藏
        builder.setNumber(2);
        //可以点击通知栏的删除按钮删除
        builder.setAutoCancel(true);
        //系统状态栏显示的小图标
       builder.setSmallIcon(R.drawable.avtar);
        //下拉显示的大图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.notification));
        Intent intent = new Intent(this,LoginActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
        //点击跳转的intent
        builder.setContentIntent(pIntent);
        //通知默认的声音 震动 呼吸灯
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        Notification notification = builder.build();
        manger.notify(TYPE_Normal,notification);
    }
    //故障通知
    private void guzhangNotify(){
        //为了版本兼容  选择V7包下的NotificationCompat进行构造
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        //Ticker是状态栏显示的提示
        builder.setTicker("简单Notification");
        //第一行内容  通常作为通知栏标题
        builder.setContentTitle("电梯故障！");
        //第二行内容 通常是通知正文
        builder.setContentText("故障电梯：id_4号电梯");
        //第三行内容 通常是内容摘要什么的 在低版本机器上不一定显示
        builder.setSubText("故障类型：门机故障");
        //ContentInfo 在通知的右侧 时间的下面 用来展示一些其他信息
        //builder.setContentInfo("2");
        //number设计用来显示同种通知的数量和ContentInfo的位置一样，如果设置了ContentInfo则number会被隐藏
        builder.setNumber(2);
        //可以点击通知栏的删除按钮删除
        builder.setAutoCancel(true);
        //系统状态栏显示的小图标
        builder.setSmallIcon(R.drawable.error);
        //下拉显示的大图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.error));
        Intent intent = new Intent(this,LoginActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
        //点击跳转的intent
        builder.setContentIntent(pIntent);
        //通知默认的声音 震动 呼吸灯
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        Notification notification = builder.build();
        manger.notify(TYPE_BigPicture,notification);
    }
}
