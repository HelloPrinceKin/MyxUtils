                                 Xutils
1 视图注入的模块
           1.1 注解的声明
             1.1.1 @Target     表示注解使用的位置
             1.1.2 @Retention  表明是注解保留的时间

           1.2 注解和注解的使用
                 要清楚知道 注解加反射的配合使用  和利弊

           1.3 视图注入不仅仅可以注入Activity的控件 还有Fragment的控件 ViewHolder的控件都可以

           1.4 事件注入
                1.4.1 注入的方法必须是private 修饰的
                1.4.2 注入的方法的返回值必须和你 所要原有的事件保持一致
                1.4.3 注入的方法的参数 必须和原有的方法保持一致
                1.4.4 通过id去指定 哪个控件注入了 通过type确定 注入的事件 类型

 总结 ：视图注入的模块原理就是反射加注解，事件注入反射加注解的基础上加上了动态代理。

2 Http 部分
      使用分为三步
         1 初始化 x.Ext.init()
         2 发送请求
         3 如果你想取消这个网络请求 Cancleable 对象 进行取消
      2.1 get请求
          2.1.1 需要一个RequestParams对象 指定路径和参数
          2.1.2 需要一个回调监听
               2.1.2.1   onSuccess
                         onError
                         onFinish
                         onCanceled


      2.2 post请求

        2.2.1需要个一请求的参数 RequestParams
        2.2.2回调监听
        2.2.3 可以利用post请求来上传文件
      总结：以前在afinal 和 xUtils 2.6以前使用的是HttpClient
            在Xutils 3.0以后全部使用了HttpUrlClient
            因为在5.0以后 HttpClient 在系统的api已经被去除了
            所以必须修改成 HttpUrlClient

3 数据库
    3.1增
                        // 1 所存的对象 的类上必须要标注表名
                        // 2 所存的字段 必须要 标注字段的名字
                        // 3 所存的类 必须要有一个id ，不然就会存不进去

                        mDbManager.save(user);
    3.2删
                              // 会根据设置的条件去删除 所有符合条件的对象
                            // 条件的设定 > < =   连接符 and两者同时满足  or 两者满足其一
                            mDbManager.delete(User.class, WhereBuilder.b("age",">",18)/*.and("userName","==","高原")*/);

    3.3改
                         KeyValue value = new KeyValue("userName","华北平原");
                         KeyValue keyValue = new KeyValue("age",18);
                         try {
                           // 修改
                            // 改哪张表   User.class
                           // 改哪些内容  WhereBuilder.b("userName","=","高原")
                           // 修改成什么  KeyValue value = new KeyValue("userName","华北平原");
                          //             KeyValue keyValue = new KeyValue("age",18);
                           mDbManager.update(User.class,WhereBuilder.b("userName","=","高原"),value,keyValue);

    3.4查
                                    // 根据条件去查找
                                     // 设定的条件 可以用whereBuilder
                                     //添加条件可以用 or and 联系
                                     //final Selector<User>selector = mDbManager.selector(User.class).where("userName", "=", "华北平原").or("age", "=", 18);
                                     final List<User> users = mDbManager.selector(User.class).where("userName", "like", "华北%").findAll();
                                    // final List<User> users = selector.findAll();

                 总结：数据库部分是基于Sqlite的是一个No SQL 的概念
                       里面重要的WhereBuilder其实对SQL语句的封装
4 图片处理


         xutils 加载图片
            x.image().bind(ImageView,"path",ImageOptions)

                                     // 设置是否使用缓存
                                    setUseMemCache(true).
                                    //设置图片是否是圆形
                                    setCircular(true).
                                    setConfig(Bitmap.Config.RGB_565).
                                    // 设置是否渐入
                                    setFadeIn(true).
                                    // 设置加载失败的图片
                                    setFailureDrawableId(R.mipmap.ic_launcher).
                                    // 设置 加载过程中的图片
                                    setLoadingDrawableId(R.mipmap.ic_launcher)
                                    // 设置 是否支持GIF 动态图
                                    .setIgnoreGif(false).build();

            图片加载部分使用三级缓存 内存缓存使用的LruCache 图片在本地也存有
                                    并且在使用了数据库 进行配合使用

