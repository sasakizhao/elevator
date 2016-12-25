package com.zhao.elevator.entity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhao.elevator.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhao on 2016/12/8.
 */

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Map<String,Object>> listItems;
    private List<String> group_list= new ArrayList<String>();
    private List<List<String>> item_list=new ArrayList<List<String>>();


    public MyExpandableListAdapter(Context context, List<Map<String,Object>> listItems) {
        this.context = context;
        this.listItems=listItems;
        group_list = new ArrayList<String>();
        for (Map<String,Object> listItem: listItems) {
            group_list.add(listItem.get("id")+"    "+listItem.get("spendTime"));
            List<String> item_lt=new ArrayList<>();
            item_lt.add((String)listItem.get("id"));
            item_lt.add((String)listItem.get("position"));
            /**
             * 汉化
             */
//            switch ((String)listItem.get("action")){
//                case "stop":
//                    Log.e("运行状态","停止");
//                    item_lt.add("停止");
//                    break;
//                case "up":
//                    Log.e("运行状态","上升");
//                    item_lt.add("上升");
//                    break;
//                case "down":
//                    Log.e("运行状态","下降");
//                    item_lt.add("下降");
//                    break;
//                default:
//                    Log.e("运行状态","未知");
//                    break;
//            }

            item_lt.add((String)listItem.get("action"));
            item_lt.add((String)listItem.get("currentWeight"));
            item_lt.add((String)listItem.get("weight"));
            item_lt.add((String)listItem.get("time"));//            String action=(String)listItem.get("action");
//            if (action.equals("up")){
//                item_lt.add("上升");
//            }else  if(action.equals("down")){
//                item_lt.add("下降");
//            }else if (action.equals("stop")){
//                item_lt.add("停止");
//            }
            item_lt.add((String)listItem.get("spendTime"));

            item_list.add(item_lt);
        }
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
        GroupHolder groupHolder = null;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.expendlist_group, null);
            groupHolder = new GroupHolder();
            /**
             * 修改 去掉图片 换成数字
             */

            groupHolder.txtContent = (TextView)convertView.findViewById(R.id.txt);
            groupHolder.txtNum = (TextView)convertView.findViewById(R.id.num);

            //       groupHolder.img = (ImageView)convertView.findViewById(R.id.img);

            convertView.setTag(groupHolder);
        }
        else
        {
            groupHolder = (GroupHolder)convertView.getTag();
        }

        if (!isExpanded)
        {
//            groupHolder.img.setBackgroundResource(R.drawable.group1);
            //  groupHolder.txtNum.setText(groupPosition);
            groupHolder.txtNum.setText(""+(groupPosition+1));

        }
        else
        {
            //   groupHolder.img.setBackgroundResource(R.drawable.group2);
            //   groupHolder.txtNum.setText(groupPosition);
            groupHolder.txtNum.setText(""+(groupPosition+1));
        }

        groupHolder.txtContent.setText(group_list.get(groupPosition));
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
        ItemHolder itemHolder = null;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.expendlist_item, null);
            itemHolder = new ItemHolder();
            itemHolder.txt = (TextView)convertView.findViewById(R.id.txt);
            itemHolder.img = (ImageView)convertView.findViewById(R.id.img);
            convertView.setTag(itemHolder);
        }
        else
        {
            itemHolder = (ItemHolder)convertView.getTag();
        }
        itemHolder.txt.setText(item_list.get(groupPosition).get(childPosition));
        itemHolder.img.setBackgroundResource(R.drawable.grey);
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
    class GroupHolder
    {
        public TextView txtContent,txtNum;

        //    public ImageView img;
    }

    class ItemHolder
    {
        public ImageView img;

        public TextView txt;
    }
}


