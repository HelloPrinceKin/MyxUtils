package com.xiexiaobo.www.day01xutils.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Bob on 2016/10/24.
 */
@Table(name = "User")
public class User {
    @Column(name = "userName")
    private String userName;
    @Column(name = "age")
    private int age;
    @Column(name = "id",isId = true)
    private int id;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
