package com.zhao.elevator.activity.xc_activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhao.elevator.R;
import com.zhao.elevator.activity.testActivity.RescueActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhao on 2016/12/17.
 */

public class XC_qunti_Activity extends Activity{
    private ExpandableListView expandableListView;

    private List<String> group_list;

    private List<String> item_lt;

    private List<List<String>> item_list;

    private List<List<Integer>> item_list2;

    private List<List<Integer>> gr_list2;

    private MyExpandableListViewAdapter adapter;


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

        // 随便一堆测试数据
        group_list = new ArrayList<String>();
        group_list.add("群体1");
        group_list.add("群体2");
        group_list.add("群体3");
        group_list.add("群体4");

        item_lt = new ArrayList<String>();
        item_lt.add("小斌");
        item_lt.add("阿威");
     //   item_lt.add("飞扬");

        item_list = new ArrayList<List<String>>();
        item_list.add(item_lt);
        item_list.add(item_lt);
        item_list.add(item_lt);
        item_list.add(item_lt);



        expandableListView = (ExpandableListView)findViewById(R.id.expandlist);
        expandableListView.setGroupIndicator(null);
        // 监听组点击
        expandableListView.setOnGroupClickListener(new OnGroupClickListener()
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
        adapter = new MyExpandableListViewAdapter(this);

        expandableListView.setAdapter(adapter);

    }




    //修改常用楼层
    void changStartFloor() {
        final String[] items = new String[]{
                "小斌 学生 G909", "阳阳 学生 E1217", "阿威 学生 G0911", "小梁 学生 G0915", "大孟 学生 E1201"};
        //用dialog修改
        AlertDialog.Builder startbuilder = new AlertDialog.Builder(XC_qunti_Activity.this)
                .setTitle("群体4")
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
    private class MyExpandableListViewAdapter extends BaseExpandableListAdapter {
        private Context context;

        public MyExpandableListViewAdapter(Context context)
        {
            this.context = context;
        }

        /**
         *
         * 获取组的个数
         *
         * @return
         * @see android.widget.ExpandableListAdapter#getGroupCount()
         */
        @Override
        public int getGroupCount()
        {
            return group_list.size();
        }

        /**
         *
         * 获取指定组中的子元素个数
         *
         * @param groupPosition
         * @return
         * @see android.widget.ExpandableListAdapter#getChildrenCount(int)
         */
        @Override
        public int getChildrenCount(int groupPosition)
        {
            return item_list.get(groupPosition).size();
        }

        /**
         *
         * 获取指定组中的数据
         *
         * @param groupPosition
         * @return
         * @see android.widget.ExpandableListAdapter#getGroup(int)
         */
        @Override
        public Object getGroup(int groupPosition)
        {
            return group_list.get(groupPosition);
        }

        /**
         *
         * 获取指定组中的指定子元素数据。
         *
         * @param groupPosition
         * @param childPosition
         * @return
         * @see android.widget.ExpandableListAdapter#getChild(int, int)
         */
        @Override
        public Object getChild(int groupPosition, int childPosition)
        {
            return item_list.get(groupPosition).get(childPosition);
        }

        /**
         *
         * 获取指定组的ID，这个组ID必须是唯一的
         *
         * @param groupPosition
         * @return
         * @see android.widget.ExpandableListAdapter#getGroupId(int)
         */
        @Override
        public long getGroupId(int groupPosition)
        {
            return groupPosition;
        }

        /**
         *
         * 获取指定组中的指定子元素ID
         *
         * @param groupPosition
         * @param childPosition
         * @return
         * @see android.widget.ExpandableListAdapter#getChildId(int, int)
         */
        @Override
        public long getChildId(int groupPosition, int childPosition)
        {
            return childPosition;
        }

        /**
         *
         * 组和子元素是否持有稳定的ID,也就是底层数据的改变不会影响到它们。
         *
         * @return
         * @see android.widget.ExpandableListAdapter#hasStableIds()
         */
        @Override
        public boolean hasStableIds()
        {
            return true;
        }

        /**
         *
         * 获取显示指定组的视图对象
         *
         * @param groupPosition 组位置
         * @param isExpanded 该组是展开状态还是伸缩状态
         * @param convertView 重用已有的视图对象
         * @param parent 返回的视图对象始终依附于的视图组
         * @return
         * @see android.widget.ExpandableListAdapter#getGroupView(int, boolean, android.view.View,
         *      android.view.ViewGroup)
         */
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
        {
            com.zhao.elevator.activity.xc_activity.GroupHolder groupHolder = null;
            if (convertView == null)
            {
                convertView = LayoutInflater.from(context).inflate(R.layout.expendlist_group, null);
                groupHolder = new com.zhao.elevator.activity.xc_activity.GroupHolder();
                groupHolder.txt = (TextView)convertView.findViewById(R.id.txt);
                convertView.setTag(groupHolder);
            }
            else
            {
                groupHolder = (com.zhao.elevator.activity.xc_activity.GroupHolder)convertView.getTag();
            }
            groupHolder.txt.setText(group_list.get(groupPosition));
            return convertView;
        }

        /**
         *
         * 获取一个视图对象，显示指定组中的指定子元素数据。
         *
         * @param groupPosition 组位置
         * @param childPosition 子元素位置
         * @param isLastChild 子元素是否处于组中的最后一个
         * @param convertView 重用已有的视图(View)对象
         * @param parent 返回的视图(View)对象始终依附于的视图组
         * @return
         * @see android.widget.ExpandableListAdapter#getChildView(int, int, boolean, android.view.View,
         *      android.view.ViewGroup)
         */
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
        {
            com.zhao.elevator.activity.xc_activity.ItemHolder itemHolder = null;
            if (convertView == null)
            {
                convertView = LayoutInflater.from(context).inflate(R.layout.expendlist_item, null);
                itemHolder = new com.zhao.elevator.activity.xc_activity.ItemHolder();
                itemHolder.txt = (TextView)convertView.findViewById(R.id.txt);
                convertView.setTag(itemHolder);
            }
            else
            {
                itemHolder = (com.zhao.elevator.activity.xc_activity.ItemHolder)convertView.getTag();
            }
            itemHolder.txt.setText(item_list.get(groupPosition).get(childPosition));
            return convertView;
        }

        /**
         *
         * 是否选中指定位置上的子元素。
         *
         * @param groupPosition
         * @param childPosition
         * @return
         * @see android.widget.ExpandableListAdapter#isChildSelectable(int, int)
         */
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition)
        {
            return true;
        }

    }


}

class GroupHolder
{
    public TextView txt;

    // public ImageView img;
}

class ItemHolder
{
    // public ImageView img;

    public TextView txt;
}



