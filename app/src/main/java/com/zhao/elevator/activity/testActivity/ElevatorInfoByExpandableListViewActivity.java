package com.zhao.elevator.activity.testActivity;

import android.support.v7.app.AppCompatActivity;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.ExpandableListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhao.elevator.R;
import com.zhao.elevator.utils.Dao;
import com.zhao.elevator.utils.Data;
import com.zhao.elevator.utils.HttpUtils;
import com.zhao.elevator.entity.*;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
/**
 * Created by zhao on 2016/12/8.
 */

public class ElevatorInfoByExpandableListViewActivity extends AppCompatActivity {
    private Data data;
    private String iPaddress="172.22.57.22";
    private String port="8080";
    private TextView startFloorTextview, endFloorTextview, promptInfo;
    private Button startFloorButton, endFloorButton, searchFirstElevator;
    private List<Map<String, Object>> listItems;
    private List<Elevator> elevatorList;
    private ExpandableListView elevatorInfoListview;
    private String url ;
    //用户输入时所处的位置和上下楼动作
    int userPos;
    int end_floor;
    String userAct;
    Handler handler;
    Comparator<Elevator> myElevatorComparator = new Comparator<Elevator>() {
        //重写用于计算电梯时间的比较器
        @Override
        public int compare(Elevator lhs, Elevator rhs) {
            double time1 = Dao.calTime(lhs, userPos, userAct);
            double time2 = Dao.calTime(rhs, userPos, userAct);
            if (time1 > time2) {
                return 1;
            } else if (time1 < time2) {
                return -1;
            } else {
                return 0;
            }
        }
    };
    Queue<Elevator> myPriorityQueue = new PriorityQueue<Elevator>(11, myElevatorComparator);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elevator_info_by_expandable_list_view);
        elevatorInfoListview = (ExpandableListView) findViewById(R.id.expandable_listview);
        /**
         * 获取全局变量
         */
        data= (Data) getApplication();
    //    iPaddress=data.getIpAddress();
     //   port=Data.PORT;
        url= "http://" + iPaddress + ":" + port +
                "/elevatorService/servlet/InfoServlet";
        UserInfo ui2 = data.getUi();
        if (ui2 != null) {
            userPos=Integer.parseInt(ui2.getStartFloor());
            end_floor=Integer.parseInt(ui2.getCommonFloor());
        }
        checkAction();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0x01:
                        String result = msg.obj.toString();
                        //                   text.setText(result);
                        jsonToList(result);//将json打包成List
                        for (Elevator ele : elevatorList) {
                            Log.i("funTest", "当前电梯为" + ele.getId());
                            //在此处将List封装成PriorityQueue
                            myPriorityQueue.add(ele);
                        }
                        elevatorList.clear();
                        while (!myPriorityQueue.isEmpty()) {
                            elevatorList.add(myPriorityQueue.poll());
                        }
                        for (Elevator ele : elevatorList) {
                            String time = ele.getId() + " 花费时间" + Dao.calTime(ele, userPos, userAct);
                            Log.e("优先队列打印", time);
                        }
                        /**
                         * 在此处更新UI
                         */
                        listItems = new ArrayList<Map<String, Object>>();
                        for (Elevator ele : elevatorList) {
                            Map<String, Object> listItem = new HashMap<>();
                            listItem.put("id", "电梯id : " + ele.getId());
                            listItem.put("position", "电梯所在位置 : " + ele.getPosition());
                            listItem.put("action", "电梯正在进行的动作 : " + ele.getAction());
                            /**
                             * 汉化
                             */
//
//                               String action=ele.getAction();
//                                if (action.equals("up")){
//                                    listItem.put("action", "电梯正在进行的动作 : " + "上升");
//                                }else  if(action.equals("down")){
//                                    listItem.put("action", "电梯正在进行的动作 : " + "下降");
//                                }else if (action.equals("stop")){
//                                    listItem.put("action", "电梯正在进行的动作 : " + "停止");
//                                }

                            listItem.put("currentWeight", "电梯容量 : " + ele.getCurrentWeight());
                            listItem.put("weight", "电梯已容纳 : " + ele.getWeight());
                            listItem.put("time", "电梯信息更新时间 : " + ele.getTime());
                            listItem.put("spendTime", "电梯到达需要:" + Dao.calTime(ele, userPos, userAct) + "秒");
                            StringBuilder dockFloor = new StringBuilder();
                            for (int i = 0; i < ele.getDockFloor().length; i++) {
                                dockFloor.append(ele.getDockFloor()[i] + "层,");
                            }

                            listItem.put("dockFloor", "电梯停靠楼层 : " + dockFloor);
                            listItems.add(listItem);
                        }

                        elevatorInfoListview.setGroupIndicator(null);
/*
                        // 监听组点击
                        elevatorInfoListview.setOnGroupClickListener(new OnGroupClickListener()
                        {
                            @SuppressLint("NewApi")
                            @Override
                            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id)
                            {
                                if (item_list.get(groupPosition).isEmpty())
                                {
                                    return true;
                                }
                                return false;
                            }
                        });
                        // 监听每个分组里子控件的点击事件
                        elevatorInfoListview.setOnChildClickListener(new OnChildClickListener()
                        {

                            @Override
                            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
                            {

                                Toast.makeText(ElevatorInfoByExpandableListViewActivity.this, "group=" + groupPosition + "---child=" + childPosition + "---" + item_list.get(groupPosition).get(childPosition), Toast.LENGTH_SHORT).show();

                                return false;
                            }
                        });
                        */
                        MyExpandableListAdapter adapter = new MyExpandableListAdapter(ElevatorInfoByExpandableListViewActivity.this, listItems);
                        elevatorInfoListview.setAdapter(adapter);
                        Log.e("sa","运行了一次handleMessage");
                        break;
                }
            }
        };
        getInfoHandle();
        //初始化各button  textview
        startFloorTextview = (TextView) findViewById(R.id.start_floor_textview);
        endFloorTextview = (TextView) findViewById(R.id.end_floor_textview);
        promptInfo = (TextView) findViewById(R.id.prompt_info);

        startFloorButton = (Button) findViewById(R.id.start_floor_button);
        endFloorButton = (Button) findViewById(R.id.end_floor_button);
        searchFirstElevator = (Button) findViewById(R.id.search_first_elevator);

        Data data = (Data) getApplication();
        UserInfo ui = data.getUi();
  //      startFloorTextview.setText(ui.getStartFloor());
  //      endFloorTextview.setText(ui.getCommonFloor());

        startFloorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changStartFloor();
            }
        });
        endFloorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changEndFloor();
            }
        });

        searchFirstElevator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //根据用户修改的起始楼层、目的楼层 重新对电梯排序
                //判断用户需要上楼还是下楼
                checkAction();
                //重新获取电梯最新数据
                getInfoHandle();
            }
        });
    }

    public void getInfoHandle() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                String result = HttpUtils.doGetAsyn(url);
                Log.i("test", result);

                Message msg = Message.obtain();
                msg.what = 0x01;
                //执行完毕后给handler发送登录成功消息
                msg.obj = result;
                handler.sendMessage(msg);
                Looper.loop();
            }
        }).start();
    }

    void jsonToList(String str) {
        Gson gson = new Gson();
        // json转为带泛型的list
        elevatorList = gson.fromJson(str,
                new TypeToken<List<Elevator>>() {
                }.getType());
    }

    //修改常用楼层
    void changStartFloor() {
        final String[] items = new String[]{
                "1楼", "2楼", "3楼", "4楼", "5楼", "6楼", "7楼", "8楼", "9楼", "10楼"};
        //用dialog修改
        AlertDialog.Builder startbuilder = new AlertDialog.Builder(ElevatorInfoByExpandableListViewActivity.this)
                .setTitle("修改起始楼层")
                .setIcon(R.drawable.logo)
                .setSingleChoiceItems(items, Integer.parseInt((String) (startFloorTextview.getText())) - 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ElevatorInfoByExpandableListViewActivity.this, "你选中了《" + items[which] + "》", Toast.LENGTH_SHORT).show();
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
                startFloorTextview.setText("" + userPos);
                Log.e("userPos", userPos + "");
                //更新全局变量信息
                Data data = (Data) getApplication();
                UserInfo ui = data.getUi();
                if (ui != null) {
                    ui.setStartFloor("" + userPos);
                    data.setUi(ui);
                    Log.e("打印全局变量", data.getUi().toString());
                } else {
                }
            }
        });
    }

    private AlertDialog.Builder setNegativeButton(
            AlertDialog.Builder builder) {
        // 调用setNegativeButton方法添加“取消”按钮
        return builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ElevatorInfoByExpandableListViewActivity.this, "单击了【取消】按钮！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 修改目的楼层
     */
    void changEndFloor() {
        final String[] items = new String[]{
                "1楼", "2楼", "3楼", "4楼", "5楼", "6楼", "7楼", "8楼", "9楼", "10楼"};
        //用dialog修改
        AlertDialog.Builder endBuilder = new AlertDialog.Builder(ElevatorInfoByExpandableListViewActivity.this)
                .setTitle("修改目的楼层")
                .setIcon(R.drawable.logo)
                .setSingleChoiceItems(items, Integer.parseInt((String) (endFloorTextview.getText())) - 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ElevatorInfoByExpandableListViewActivity.this, "你选中了《" + items[which] + "》", Toast.LENGTH_SHORT).show();
                        end_floor = which + 1;
                    }
                });
        // 为AlertDialog.Builder添加“确定”按钮
        setEndPositiveButton(endBuilder);
        // 为AlertDialog.Builder添加“取消”按钮
        setNegativeButton(endBuilder)
                .create()
                .show();
    }

    private AlertDialog.Builder setEndPositiveButton(
            AlertDialog.Builder builder) {
        // 调用setPositiveButton方法添加“确定”按钮
        return builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                endFloorTextview.setText("" + end_floor);
                Log.e("end_floor", end_floor + "");
                //更新全局变量信息
                Data data = (Data) getApplication();
                UserInfo ui = data.getUi();
                if (ui != null) {
                    ui.setCommonFloor(end_floor + "");
                    data.setUi(ui);
                    Log.e("打印全局变量", data.getUi().toString());
                } else {
                }
            }
        });
    }

    void checkAction() {
        //判断用户需要上楼还是下楼
        if (userPos == end_floor) {
            userAct = "stop";
        } else if (userPos > end_floor) {
            userAct = "down";
        } else if (userPos < end_floor) {
            userAct = "up";
        }
    }

}

