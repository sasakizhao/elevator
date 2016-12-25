package com.zhao.elevator.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhao.elevator.R;
import com.zhao.elevator.frag.FragmentAdapter;

/**
 * Created by zhao on 2016/12/8.
 */

public class MainIndexActivity extends FragmentActivity implements View.OnClickListener {
    public static final int TAB_HOME = 0;
    public static final int TAB_FAST_ELEVATOR = 1;
    public static final int TAB_USER_INFO = 2;
    // 底部菜单3个Linearlayout
    private LinearLayout llHome,llFastElevator,llUserInfo;
    // 底部菜单3个ImageView
    private ImageView ivHome,ivFastElevator,ivUserInfo;
    // 底部菜单3个菜单标题
    private TextView tvHome,tvFastElevator,tvUserInfo;


    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_main);
        initView();
        addListener();
    }

    private void initView() {
        // 底部菜单3个Linearlayout
        this.llHome = (LinearLayout) findViewById(R.id.ll_home);
        this.llFastElevator = (LinearLayout) findViewById(R.id.ll_fast_elevator);
        this.llUserInfo = (LinearLayout) findViewById(R.id.ll_user_info);
        // 底部菜单4个ImageView
        this.ivHome = (ImageView) findViewById(R.id.iv_home);
        this.ivFastElevator = (ImageView) findViewById(R.id.iv_fast_elevator);
        this.ivUserInfo = (ImageView) findViewById(R.id.iv_user_info);
        // 底部菜单4个菜单标题
        this.tvHome = (TextView) findViewById(R.id.tv_home);
        this.tvFastElevator = (TextView) findViewById(R.id.tv_fast_elevator);
        this.tvUserInfo = (TextView) findViewById(R.id.tv_user_info);
        // 中间内容区域ViewPager
        this.viewPager = (ViewPager) findViewById(R.id.vp_content);


        llHome.setOnClickListener(this);
        llFastElevator.setOnClickListener(this);
        llUserInfo.setOnClickListener(this);

        //初始化适配器
        FragmentAdapter adapter = new FragmentAdapter(
                getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    private void addListener() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int id) {
                switch (id) {
                    case TAB_HOME:
                        //		main_tab_home.setChecked(true);
                        Log.e("test",TAB_HOME+"");
                        break;
                    case TAB_FAST_ELEVATOR:
                        //		main_tab_catagory.setChecked(true);
                        Log.e("test",TAB_FAST_ELEVATOR+"");
                        break;
                    case TAB_USER_INFO:
                        //		main_tab_car.setChecked(true);
                        Log.e("test",TAB_USER_INFO+"");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
                viewPager.setCurrentItem(TAB_HOME);
                break;
            case R.id.ll_fast_elevator:
                viewPager.setCurrentItem(TAB_FAST_ELEVATOR);
                break;
            case R.id.ll_user_info:
                viewPager.setCurrentItem(TAB_USER_INFO);
                break;
            default:
                break;
        }
    }
}
