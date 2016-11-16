package com.xiexiaobo.www.day01xutils;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.xiexiaobo.www.day01xutils.config.NetConfig;

import org.xutils.image.ImageOptions;
import org.xutils.x;

public class ImageActivity extends AppCompatActivity {
    private ImageView mImageView;
    private ImageOptions mOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        initView();
        initData();
    }

    private void initData() {
        mOptions = new ImageOptions.Builder().
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
    }

    private void initView() {
        mImageView = (ImageView) findViewById(R.id.iv_image_test);
    }

    public void onClick(View view){
        x.image().bind(mImageView, NetConfig.IMAGE_PATH,mOptions);
    }
}
