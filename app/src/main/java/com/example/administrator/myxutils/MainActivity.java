package com.example.administrator.myxutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.xutils.DbManager;
import org.xutils.db.Selector;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.name_id)EditText mName;
    @ViewInject(R.id.sex_id)EditText mSex;
    @ViewInject(R.id.age_id)EditText mAge;
    @ViewInject(R.id.list_id)ListView mList;
    @ViewInject(R.id.text)TextView mText;
    private DbManager dbManager;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        mList.setEmptyView(mText);
        DbManager.DaoConfig config=new DbManager.DaoConfig();
        dbManager = x.getDb(config);
        aboutListView();

    }


    private void aboutListView() {
        List<Student> data = new LinkedList<>();
        try {
            List<Student> all = dbManager.selector(Student.class).findAll();
            if(all!=null){
                data.addAll(all);
            }
           // Log.i("data", all.toString());
         //   data.addAll(all);
            adapter = new ArrayAdapter(this, android.R.layout
                    .simple_list_item_1, data);
            mList.setAdapter(adapter);
        } catch (DbException e) {
            e.printStackTrace();
        }


    }

    @Event({R.id.add_id,R.id.delete_id})
    private void clickAction(View view){

        switch(view.getId()){
            case R.id.add_id:
                try {
                    Student student=new Student();
                    student.setName(mName.getText().toString());
                    student.setSex(mSex.getText().toString());
                    student.setAge(Integer.parseInt(mAge.getText().toString()));
                    dbManager.save(student);
                    aboutListView();

                } catch (DbException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.delete_id:
                // mDbManager.delete(User.class, WhereBuilder.b("age",">",18)/*.and("userName","==","高原")*/);

                try {
                    dbManager.delete(Student.class, WhereBuilder.b("name", "==", mName.getText
                            ().toString()));
                } catch (DbException e) {
                    e.printStackTrace();
                }

                break;


        }

    }


}
