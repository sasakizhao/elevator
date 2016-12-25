package com.zhao.elevator.activity.testActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.zhao.elevator.R;
import com.zhao.elevator.entity.Elevator;
import com.zhao.elevator.utils.Dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by zhao on 2016/12/8.
 */

public class FasteleActivity extends Activity{
    private List<Elevator> eleList;


  //  expandable_listview
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(R.layout.activity_index_main2);


    }



    private void init() {
        Elevator ele1=new Elevator("ele_01","2","stop","1500","3000",new String[]{"2"},"2016-03-21 10:14:57");
        Elevator ele2=new Elevator("ele_02","1","up","1600","3100",new String[]{"2","3","4","5"},"2016-03-21 10:15:35");
        Elevator ele3=new Elevator("ele_03","4","stop","0","3100",new String[]{"4"},"2016-03-21 10:16:19");
        Elevator ele4=new Elevator("ele_04","8","down","1000","3000",new String[]{"1"},"2016-03-21 10:16:55");
        Elevator ele5=new Elevator("ele_04","4","down","500","3000",new String[]{"1","9"},"2016-05-04 15:29:28");
        eleList=new ArrayList<>();
        eleList.add(ele1);
        eleList.add(ele2);
        eleList.add(ele3);
        eleList.add(ele4);
        eleList.add(ele5);

    }
}
