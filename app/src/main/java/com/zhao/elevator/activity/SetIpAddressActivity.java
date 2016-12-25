package com.zhao.elevator.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.zhao.elevator.R;
import com.zhao.elevator.utils.Dao;
import com.zhao.elevator.utils.Data;
import com.zhao.elevator.utils.HttpUtils;
import com.zhao.elevator.entity.Elevator;
import com.zhao.elevator.entity.UserInfo;
/**
 * Created by zhao on 2016/12/8.
 */

public class SetIpAddressActivity extends Activity {
    private EditText inputIpAddress;
    private Button saveIpAddress;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private String ipaddress="";
    private Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_ip_address);
        inputIpAddress= (EditText) findViewById(R.id.inputIpAddress);
        saveIpAddress= (Button) findViewById(R.id.saveIpAddress);
        // 获取只能被本应用程序读、写的SharedPreferences对象
        preferences = getSharedPreferences("elevator", MODE_PRIVATE);
        editor = preferences.edit();
        data= (Data) getApplication();
        data.setPreferences(preferences);
        data.setEditor(editor);
        ipaddress= preferences.getString("ip","");
        if(ipaddress.equals("")==false){
            inputIpAddress.setText(ipaddress);
        }
        saveIpAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ipaddress=inputIpAddress.getText().toString();
                data.setIpAddress(ipaddress);
                // 存入ip地址
                editor.putString("ip",ipaddress);
                // 提交所有存入的数据
                editor.commit();
                Intent intent=new Intent(SetIpAddressActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
