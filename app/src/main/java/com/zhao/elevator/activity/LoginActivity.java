package com.zhao.elevator.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhao.elevator.R;
import com.zhao.elevator.utils.Dao;
import com.zhao.elevator.utils.Data;
import com.zhao.elevator.utils.HttpUtils;
import com.zhao.elevator.entity.Elevator;
import com.zhao.elevator.entity.UserInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**功能：实现用户登录
 * 绑定界面 login.xml
 * 与服务器交互url=""
 *
 */

public class LoginActivity extends Activity {
    private Data data;
    private String iPaddress;
    private String port;
    private TextView username, password;
    private Button loginBTN, registerBTN, visitorBTN;
    private String usernameData, passwordData;
    private String loginURL;
    private String userInfoURL;
    Handler handler;
    /**
     * SharedPreferences 用于获取和存储用户信息
     */
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        /**
         * 获取全局变量
         */
        data= (Data) getApplication();
        iPaddress=data.getIpAddress();
        port=Data.PORT;
        loginURL= "http://" + iPaddress + ":" + port +
                "/elevatorService/servlet/LoginServlet";
        userInfoURL= "http://" + iPaddress + ":" + port +
                "/elevatorService/servlet/UserInfoServlet";
        //绑定id
        username = (TextView) findViewById(R.id.accountEt_from_login);
        password = (TextView) findViewById(R.id.pwdEt_from_login);
        loginBTN = (Button) findViewById(R.id.login_btn_from_login);
        registerBTN = (Button) findViewById(R.id.register_btn_from_login);
      //  visitorBTN = (Button) findViewById(R.id.visitor_btn_from_login);
        //定义Handler对象
        handler = new Handler() {
            @Override
            //当有消息发送出来的时候就执行Handler的这个方法
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                // TODO 处理UI
                switch (msg.what) {
                    case 0x01:
                        break;
                    case 0x011:
                        String result=(String)msg.obj;
                        Log.e("result", result);
                        List<UserInfo> userInfoList=jsonToList(result);
                        if(!userInfoList.isEmpty()){
                            for (UserInfo ui: userInfoList) {
                                data.setUi(ui);
                            }
                        }
                        break;
                }
            }
        };
        loginBTN.setOnClickListener(new View.OnClickListener() {
            //登录按键监听
            @Override
            public void onClick(View v) {
                usernameData = username.getText().toString();
                passwordData = password.getText().toString();
 /*               //将usernameData 和passwordData发送到服务器校验
                ///< 发送用户名和密码到服务器进行校验，并获得服务器返回值
                loginHandle(usernameData, passwordData);
                //获取用户所有信息  存入全局变量中
                getUserInfoHandle(usernameData);*/

                Intent intent = new Intent(LoginActivity.this, MainIndexActivity.class);
                startActivity(intent);
                Toast.makeText(LoginActivity.this,usernameData+" 登录成功！",Toast.LENGTH_LONG).show();
            }

        });

        registerBTN.setOnClickListener(new View.OnClickListener() {
            //注册按键跳转到注册界面
            @Override
            public void onClick(View v) {
                Intent intentTest = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentTest);
            }
        });
/*        visitorBTN.setOnClickListener(new View.OnClickListener() {
            //游客按键跳转到主界面
            @Override
            public void onClick(View v) {
                //临时修改
          //      Intent intentTest = new Intent(LoginActivity.this, IndexActivity.class);
         //       startActivity(intentTest);
            }
        });*/

    }

    public void loginHandle(final String userName, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ///< 发送用户名和密码到服务器进行校验，并获得服务器返回值
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", userName);
                params.put("password", password);
                String encode = "utf-8";

                String result = HttpUtils.doPostAsyn(loginURL, params, encode);
                Log.e("test", result);
                Looper.prepare();
                if ("loginSuccess".equals(result)) {
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainIndexActivity.class);
                    startActivity(intent);
                }
              /*  else if ("wrongUserName".equals(result))
                {
                    Toast.makeText(LoginActivity.this, "用户名错误!", Toast.LENGTH_SHORT).show();
                }*/
                else if ("wrongPassword".equals(result)) {
                    Toast.makeText(LoginActivity.this, "密码错误!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "异常!", Toast.LENGTH_SHORT).show();
                }
                Looper.loop();
                Message msg = Message.obtain();
                msg.what = 0x01;
                //执行完毕后给handler发送登录成功消息
                handler.sendEmptyMessage(0);
            }
        }).start();
    }

    public void getUserInfoHandle(final String userName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ///< 发送用户名和密码到服务器进行校验，并获得服务器返回值
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", userName);
                String encode = "utf-8";

                String result = HttpUtils.doPostAsyn(userInfoURL, params, encode);
                //      Log.e("test", result);
                Message msg = Message.obtain();
                msg.what = 0x011;
                msg.obj = result;
                //执行完毕后给handler发送登录成功消息
                handler.sendMessage(msg);
            }
        }).start();
    }

    List<UserInfo> jsonToList(String str) {
        Gson gson = new Gson();
        // json转为带泛型的list
        List<UserInfo> userInfoList;
        userInfoList = gson.fromJson(str,
                new TypeToken<List<UserInfo>>() {
                }.getType());
        return userInfoList;
    }
}
