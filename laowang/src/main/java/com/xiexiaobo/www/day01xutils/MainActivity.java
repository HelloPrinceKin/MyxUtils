package com.xiexiaobo.www.day01xutils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_main_db:
                startActivity(new Intent(this,DBActivity.class));
                break;
            case R.id.bt_main_http:
                startActivity(new Intent(this,HttpActivity.class));
                break;
            case R.id.bt_main_iamge:
                startActivity(new Intent(this,ImageActivity.class));
                break;
            case R.id.bt_main_view:
                startActivity(new Intent(this,ViewActivity.class));
                break;
            default:
                break;
        }
    }
}
