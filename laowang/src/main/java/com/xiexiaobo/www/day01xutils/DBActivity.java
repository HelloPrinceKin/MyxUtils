package com.xiexiaobo.www.day01xutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.xiexiaobo.www.day01xutils.bean.User;

import org.xutils.DbManager;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.List;

public class DBActivity extends AppCompatActivity {

    private DbManager mDbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        DbManager.DaoConfig config = new DbManager.DaoConfig();
        // 设置数据库存放的位置
        //config.setDbDir(new File(""));
        // 设置数据库的名字
       // config.setDbName("");
        // 设置数据库打开的监听
        //config.setDbOpenListener();
        // 设置数据库的版本
        //config.setDbVersion();
        // 设置数据库版本更新的监听
        //config.setDbUpgradeListener();
        mDbManager = x.getDb(config);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_db_add:
                User user = new User();
                user.setUserName("高原");
                user.setAge(19);
                try {
                    // 1 所存的对象 的类上必须要标注表名
                    // 2 所存的字段 必须要 标注字段的名字
                    // 3 所存的类 必须要有一个id ，不然就会存不进去

                    mDbManager.save(user);

                } catch (DbException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bt_db_delete:
                try {
                    User deleteUser = new User();
                   // deleteUser.setAge(1);
                   // deleteUser.setUserName("ejfew");
                    // 会根据设置的条件去删除 所有符合条件的对象
                    // 条件的设定 > < =   连接符 and两者同时满足  or 两者满足其一
                    mDbManager.delete(User.class, WhereBuilder.b("age",">",18)/*.and("userName","==","高原")*/);
                   // deleteUser.setId(2);
                    // 这个是根据id去删除的 一般情况不常用
                   // mDbManager.deleteById(User.class,deleteUser);
                } catch (DbException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bt_db_update:
                KeyValue value = new KeyValue("userName","华北平原");
                KeyValue keyValue = new KeyValue("age",18);
                try {
                    // 修改
                     // 改哪张表   User.class
                    // 改哪些内容  WhereBuilder.b("userName","=","高原")
                    // 修改成什么  KeyValue value = new KeyValue("userName","华北平原");
                   //             KeyValue keyValue = new KeyValue("age",18);
                    mDbManager.update(User.class,WhereBuilder.b("userName","=","高原"),value,keyValue);
                } catch (DbException e) {
                    e.printStackTrace();
                }
                break;
            //nihao
            case R.id.bt_db_query:
                try {
                    // 根据条件去查找
                    // 设定的条件 可以用whereBuilder
                    //添加条件可以用 or and 联系
                    //final Selector<User>selector = mDbManager.selector(User.class).where("userName", "=", "华北平原").or("age", "=", 18);
                    final List<User> users = mDbManager.selector(User.class).where("userName", "like", "华北%").findAll();
                   // final List<User> users = selector.findAll();
                    if (users==null){
                        return;
                    }
                    for (int i = 0; i < users.size(); i++) {
                        Log.d("TAG", "onClick: user == "+users.get(i).getUserName());
                    }
                } catch (DbException e) {
                    e.printStackTrace();
                }

                break;
            default:
                break;
        }
    }
}
