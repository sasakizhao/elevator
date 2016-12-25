package com.zhao.elevator.frag;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhao.elevator.activity.MainIndexActivity;

/**
 * Created by zhao on 2016/12/8.
 */

public class FragmentAdapter  extends FragmentPagerAdapter {
    public final static int TAB_COUNT = 3;
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int id) {
        switch (id) {
            case MainIndexActivity.TAB_HOME:
                //	HomeFragment homeFragment = new HomeFragment();
                WebFragment webFragment=new WebFragment();


                return webFragment;
            case MainIndexActivity.TAB_FAST_ELEVATOR:
                FastElevatorFragment fastElevatorFragment = new FastElevatorFragment();
                return fastElevatorFragment;
            case MainIndexActivity.TAB_USER_INFO:
                UserInfoFragment userInfoFragment = new UserInfoFragment();
                return userInfoFragment;
        }
        return null;
    }
    @Override
    public int getCount() {
        return TAB_COUNT;
    }
}
