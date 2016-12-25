package com.zhao.elevator.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.zhao.elevator.R;


import com.zhao.elevator.utils.Data;
import com.zhao.elevator.utils.HttpUtils;



import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhao on 2016/12/8.
 */

public class RegisterActivity extends Activity {
    private Data data;
    private String iPaddress;
    private String port;
    private String registerURL ;
    private TextView username,password1,password2,startFloorEt,endFloorEt;
    Button register;
    Handler handler;


    //测试
    private static final String[] m1={"乘梯用户","维修人员"};

    private static final String[] m={"普通用户","管理员"};
    private TextView view ;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        spinner = (Spinner) findViewById(R.id.spinner1);
        //将可选内容与ArrayAdapter连接起来
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m);

        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //将adapter 添加到spinner中
        spinner.setAdapter(adapter);

        //添加事件Spinner事件监听
    //    spinner.setOnItemSelectedListener(new SpinnerSelectedListener());

        //设置默认值
        spinner.setVisibility(View.VISIBLE);







        /**
         * 获取全局变量
         */
        data= (Data) getApplication();
        iPaddress=data.getIpAddress();
        port=Data.PORT;
        registerURL= "http://" + iPaddress + ":" + port +
                "/elevatorService/servlet/RegisterServlet";
        handler =new Handler(){
            @Override
            //当有消息发送出来的时候就执行Handler的这个方法
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                // TODO 处理UI
                switch (msg.what)
                {
                    case 0x02:
                        String result=(String)msg.obj;
                        break;
                }
            }
        };
        findViews();
        setListeners();
        //定义Handler对象
    }
    private void findViews()
    {
        username= (TextView) findViewById(R.id.accountEt_from_register);
        password1= (TextView) findViewById(R.id.pwdEt1_from_register);
        password2= (TextView) findViewById(R.id.pwdEt2_from_register);
     //   startFloorEt= (TextView) findViewById(R.id.start_floorEt_from_register);
    //    endFloorEt= (TextView) findViewById(R.id.end_floorEt_from_register);
        register= (Button) findViewById(R.id.registerBtn_from_register);
    }
    private void setListeners(){
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //实现用户注册
                String usernameData=username.getText().toString();
                String passwordData1=password1.getText().toString();
                String passwordData2=password2.getText().toString();
                String startFloorData=startFloorEt.getText().toString();
                String endFloorData=endFloorEt.getText().toString();
                if(passwordData1.equals(passwordData2)){
                    //提交到服务器
                    registerHandle(usernameData, passwordData1,startFloorData,endFloorData);
                }
            }
        });
    }

    private void registerHandle(final String usernameData, final String passwordData1, final String startFloorData, final String endFloorData)    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                Looper.prepare();
                ///< 发送用户名和密码到服务器进行校验，并获得服务器返回值
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", usernameData);
                params.put("password", passwordData1);
                params.put("startFloor", startFloorData);
                params.put("endFloor", endFloorData);
                String encode = "utf-8";
                String result = HttpUtils.doPostAsyn(registerURL, params, encode);

                Log.i("test", result);
                if ("registerSuccess".equals(result))
                {
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                }else if("Account exists".equals(result)){
                    Toast.makeText(RegisterActivity.this, "注册失败帐号存在", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegisterActivity.this, "注册失败原因未知", Toast.LENGTH_SHORT).show();
                }

                Looper.loop();
                Message msg= Message.obtain();
                msg.what=0x02;
                //执行完毕后给handler发送登录成功消息
                msg.obj=result;
                handler.sendMessage(msg);
            }
        }).start();
    }
}
