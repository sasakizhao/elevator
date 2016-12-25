package com.zhao.elevator.frag;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.zhao.elevator.entity.*;
import com.zhao.elevator.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhao.elevator.utils.Data;
import com.zhao.elevator.utils.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhao on 2016/12/8.
 */

public class UserInfoFragment extends Fragment {
    private Data data;
    private String iPaddress;
    private String port;
    public static String userInfoURL;
    Handler handler;
    TextView username,userId,startFloor,commonlyFloor;
    Button changCommonlyFloor,changStartFloor;
    int changedFloor=0;
    int userPos=0;
    UserInfo ui;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.user_info, container, false);
        username= (TextView) view.findViewById(R.id.username);
        userId= (TextView) view.findViewById(R.id.userid);
        startFloor= (TextView) view.findViewById(R.id.startFloor);
        commonlyFloor= (TextView) view.findViewById(R.id.commonly_floor);
        changCommonlyFloor= (Button) view.findViewById(R.id.chang_commonly_floor);
        changStartFloor=(Button) view.findViewById(R.id.chang_start_floor);
        /**
         * 获取全局变量
         */
        data= (Data) getActivity().getApplication();
        iPaddress=data.getIpAddress();
        port=Data.PORT;
        userInfoURL= "http://" + iPaddress + ":" + port +
                "/elevatorService/servlet/UserInfoServlet";
        //通过全局变量更新用户信息
        ui=data.getUi();
        if(ui!=null)
        {
            userId.setText(ui.getId());
            username.setText(ui.getUsername());
            commonlyFloor.setText(ui.getCommonFloor());
            startFloor.setText(ui.getStartFloor());
        }


        //  getUserInfoHandle("admin");
        //定义Handler对象
        handler =new Handler(){
            @Override
            //当有消息发送出来的时候就执行Handler的这个方法
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                // TODO 处理UI
                switch (msg.what)
                {
                    case 0x03:
                        String result=(String)msg.obj;
                        Log.e("test", result);
                        List<UserInfo> userInfoList=jsonToList(result);
                        for (UserInfo ui: userInfoList) {
                            Log.i("test",ui.toString());
                            userId.setText(ui.getId());
                            username.setText(ui.getUsername());
                            startFloor.setText(ui.getStartFloor());
                            commonlyFloor.setText(ui.getCommonFloor());
                        }
                        break;
                    case 0x13:
                        String changeResult=(String)msg.obj;
                        if(changeResult.equals("1")){
                            Toast.makeText(getActivity(),"常用楼层修改成功", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        };

        changCommonlyFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changTheConmonlyFloor();
            }
        });

        changStartFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changStartFloor();
            }
        });

        return view;
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
                msg.what = 0x03;
                msg.obj=result;
                //执行完毕后给handler发送登录成功消息
                handler.sendMessage(msg);
            }
        }).start();
    }

    /**
     * updateUserInfoHandle
     * 上传数据
     * @param userName
     * @param changedFloor
     */
    public void updateUserInfoHandle(final String userName, final int changedFloor, final String upFloorDataType) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ///< 发送用户名和密码到服务器进行校验，并获得服务器返回值
                Map<String, String> params = new HashMap<String, String>();
                params.put("updateByUsername", userName);
                //		params.put("updateBycommonFloor", ""+changedFloor);
                params.put(upFloorDataType, ""+changedFloor);
                String encode = "utf-8";

                String result = HttpUtils.doPostAsyn(userInfoURL, params, encode);
                //      Log.e("test", result);
                Message msg = Message.obtain();
                Log.e("判断是否修改成功",result);
                msg.what = 0x13;
                msg.obj=result;
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
    //修改常用楼层
    void changTheConmonlyFloor()
    {
        final String[] items = new String[] {
                "1楼", "2楼","3楼","4楼","5楼","6楼","7楼","8楼","9楼","10楼" };
        //用dialog修改
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity())
                .setTitle("修改常用楼层")
                .setIcon(R.drawable.logo)
                .setSingleChoiceItems(items, Integer.parseInt((String)(commonlyFloor.getText()))-1, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(getActivity(),"你选中了《" + items[which] + "》", Toast.LENGTH_SHORT).show();
                        changedFloor=which+1;
                    }
                });
        // 为AlertDialog.Builder添加“确定”按钮
        setPositiveButton(builder);
        // 为AlertDialog.Builder添加“取消”按钮
        setNegativeButton(builder)
                .create()
                .show();
    }
    private AlertDialog.Builder setPositiveButton(
            AlertDialog.Builder builder)
    {
        // 调用setPositiveButton方法添加“确定”按钮
        return builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                commonlyFloor.setText(""+changedFloor);
                //更新全局变量信息
                Data data= (Data) getActivity().getApplication();
                UserInfo ui=data.getUi();
                if(ui!=null)
                {
                    ui.setCommonFloor(""+changedFloor);
                    data.setUi(ui);
                    Log.e("打印全局变量",data.getUi().toString());
                }else{}
                //上传到服务器更新用户信息
                updateUserInfoHandle(ui.getUsername(),changedFloor,"updateBycommonFloor");
                ui.setCommonFloor(changedFloor+"");
            }
        });
    }
    private AlertDialog.Builder setNegativeButton(
            AlertDialog.Builder builder)
    {
        // 调用setNegativeButton方法添加“取消”按钮
        return builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Toast.makeText(getActivity(),"单击了【取消】按钮！", Toast.LENGTH_SHORT).show();
            }
        });
    }
    /**
     * 修改起始楼层
     */
    void changStartFloor() {
        final String[] items = new String[]{
                "1楼", "2楼", "3楼", "4楼", "5楼", "6楼", "7楼", "8楼", "9楼", "10楼"};
        //用dialog修改
        AlertDialog.Builder startbuilder = new AlertDialog.Builder(getActivity())
                .setTitle("修改起始楼层")
                .setIcon(R.drawable.logo)
                .setSingleChoiceItems(items, Integer.parseInt((String) (startFloor.getText())) - 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "你选中了《" + items[which] + "》", Toast.LENGTH_SHORT).show();
                        userPos = which + 1;
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
                startFloor.setText("" + userPos);
                Log.e("userPos", userPos + "");
                //更新全局变量信息
                Data data = (Data) getActivity().getApplication();
                UserInfo ui = data.getUi();
                if (ui != null) {
                    ui.setStartFloor("" + userPos);
                    data.setUi(ui);
                    Log.e("打印全局变量", data.getUi().toString());
                }
                //上传到服务器更新用户信息
                updateUserInfoHandle(ui.getUsername(),userPos,"updateByStartFloor");
                ui.setStartFloor(userPos+"");
            }
        });
    }
}
