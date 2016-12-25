package com.zhao.elevator.activity.testActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhao.elevator.R;

/**
 * Created by zhao on 2016/12/8.
 */

public class M2Act extends Activity{
    Button btn1,btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_test);
        btn1= (Button) findViewById(R.id.button5);
        btn2=(Button) findViewById(R.id.button6);
        btn3=(Button) findViewById(R.id.button7);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(M2Act.this,FasteleActivity.class);
                startActivity(intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    Intent intent=new Intent(M2Act.this,);
          //      startActivity(intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
       //         Intent intent=new Intent(M2Act.this,);
      //          startActivity(intent);
            }
        });
    }

}
