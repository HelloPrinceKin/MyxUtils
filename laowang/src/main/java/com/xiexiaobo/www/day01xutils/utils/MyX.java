package com.xiexiaobo.www.day01xutils.utils;

import android.app.Activity;
import android.view.View;

import com.xiexiaobo.www.day01xutils.annotation.MyViewInject;

import java.lang.reflect.Field;

/**
 * Created by Bob on 2016/10/24.
 */
public class MyX {

    public static void inject(Activity activity){
        // 1 获取这个类上的所有的字段
        Field[] fields = activity.getClass().getDeclaredFields();


        for (int i = 0; i < fields.length; i++) {
            // 2 查看这个 字段上有没有我们自己声明的注解
             Field field = fields[i];
             MyViewInject annotation = field.getAnnotation(MyViewInject.class);
            // 3 如果有 那么就 获取注解的id
            if (annotation!=null){
                int id = annotation.id();
                // 4 通过id 去findViewById
                View viewById = activity.findViewById(id);
                // 5 给这个字段赋值
                // 暴力反射 为了解决这个字段是private protect 的情况
                // 暴力反射他有能 改变一切字段或者调用一切方法。那么在反射面前修饰符就失去意义
                field.setAccessible(true);
                try {
                    field.set(activity,viewById);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }

        }



    };
}
