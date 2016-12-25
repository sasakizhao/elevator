package com.zhao.elevator.frag;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.zhao.elevator.R;

/**
 * Created by zhao on 2016/12/8.
 */

public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_index, container, false);

        Button loginBtnFromIndex,registerBtnFromIndex,infoBtnFromIndex,infoBtnFromIndex2,userInfoBtnFromIndex;
        loginBtnFromIndex= (Button) view.findViewById(R.id.login_btn_from_index);
        registerBtnFromIndex= (Button) view.findViewById(R.id.register_btn_from_index);
        infoBtnFromIndex= (Button) view.findViewById(R.id.info_btn_from_index);
        infoBtnFromIndex2= (Button) view.findViewById(R.id.info_btn2_from_index);
        userInfoBtnFromIndex= (Button) view.findViewById(R.id.user_info_btn_from_index);
        loginBtnFromIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //     startActivity(new Intent(getActivity(),LoginActivity.class));
            }
        });
        registerBtnFromIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   startActivity(new Intent(getActivity(),RegisterActivity.class));
            }
        });
        infoBtnFromIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    startActivity(new Intent(getActivity(),ElevatorInfoActivity.class));
            }
        });
        infoBtnFromIndex2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    startActivity(new Intent(getActivity(),ElevatorInfoByExpandableListViewActivity.class));
            }
        });
        userInfoBtnFromIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   startActivity(new Intent(getActivity(),UserinfoActivity.class));
            }
        });

        return view;
    }
}
