package com.xiexiaobo.www.day01xutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiexiaobo.www.day01xutils.adapter.MyAdapter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_view)
public class ViewActivity extends AppCompatActivity {
    @ViewInject( R.id.tv_view_test)
    private TextView mTextView;
    @ViewInject(R.id.bt_view_test)
    private Button mButton;
    @ViewInject(R.id.lv_view_test)
    private ListView mListView;

    private BaseAdapter mAdapter;
    private List<String> mData;
/*
    @MyViewInject(id = R.id.tv_view_test)
    private TextView mTextView;
    @MyViewInject(id = R.id.bt_view_test)
    private Button mButton;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_view);
        x.view().inject(this);
        //MyX.inject(this);
        initView();
        initData();
        setData();

    }
    // 以后再写代码的只有注释才能有中文，这里主要告诉你们方法名不重要
    @Event(type = AdapterView.OnItemClickListener.class,value = R.id.lv_view_test)
    private void 方法名不重要(AdapterView<?> parent, View view, int position, long id){
        Toast.makeText(ViewActivity.this, mData.get(position), Toast.LENGTH_SHORT).show();
    }


    private void setData() {
        mListView.setAdapter(mAdapter);
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
           mData.add("item == "+i);
        }
        mAdapter = new  MyAdapter(mData,this);
    }

    private void initView() {
       // mTextView = (TextView) findViewById(R.id.tv_view_test);
/*        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewActivity.this, "fewfwefew", Toast.LENGTH_SHORT).show();
            }
        });*/
    }
    @Event({R.id.bt_view_test,R.id.tv_view_test})
    private void 这是通过Xutils注入的点击事件各位注意方法名不重要(View view){
        switch (view.getId()){
            case R.id.bt_main_view:
                break;
            case R.id.tv_view_test:
                break;
        }
        String s = mTextView.getText().toString();
        Toast.makeText(ViewActivity.this, s, Toast.LENGTH_SHORT).show();
    }

/*    public void onClick(View view) {
        String s = mTextView.getText().toString();
        Toast.makeText(ViewActivity.this, s, Toast.LENGTH_SHORT).show();
    }*/

}
