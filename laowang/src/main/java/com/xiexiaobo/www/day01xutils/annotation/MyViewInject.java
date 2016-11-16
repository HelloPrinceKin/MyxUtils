package com.xiexiaobo.www.day01xutils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Bob on 2016/10/24.
 */
// 元注解  说明注解使用的位置
@Target(ElementType.FIELD)
// 元注解  说明注解保存的时间
@Retention(RetentionPolicy.CLASS)
public @interface MyViewInject {
    int id();
}
