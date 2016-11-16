package com.xiexiaobo.www.day01xutils;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.xiexiaobo.www.day01xutils.config.NetConfig;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

public class HttpActivity extends AppCompatActivity {
    // 用于判断Xutils发出的网络请求有没有被取消，也可以用来直接取消网络请求
    private Callback.Cancelable mCancelable;
    private ProgressDialog mDialog;
    private Callback.Cancelable mPostCancleable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        initView();
        //x.Ext.init(getApplication()); 这种初始化的操作不会方法activity里面

    }

    private void initView() {
        mDialog = new ProgressDialog(this);
        mDialog.setTitle("正在为您拼命加载。。。");
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_http_get:
                mDialog.show();
                // 他是用来存放请求的路径和参数的独享
                RequestParams params  = new RequestParams(NetConfig.GET_PATH);
                mCancelable = x.http().get(params, new Callback.CommonCallback<String>() {
                    // 成功的回调监听
                    @Override
                    public void onSuccess(String s) {
                        Toast.makeText(HttpActivity.this, s, Toast.LENGTH_SHORT).show();
                        /*String test = null;
                        test.toString();*/
                    }
                        // 错误的回调监听
                    @Override
                    public void onError(Throwable throwable, boolean b) {
                        Toast.makeText(HttpActivity.this, "网络请求不成功，请检查网络", Toast.LENGTH_SHORT).show();
                    }
                     //取消的回调监听
                    @Override
                    public void onCancelled(CancelledException e) {
                        mDialog.dismiss();
                    }
                    //结束的回调监听
                    @Override
                    public void onFinished() {
                      mDialog.dismiss();
                    }
                });
                break;
            case R.id.bt_http_post:
                RequestParams postParams = new RequestParams(NetConfig.POST_PATH);
                postParams.addBodyParameter("username","123");
                postParams.addBodyParameter("pwd","123");
                mPostCancleable = x.http().post(postParams, new Callback.CacheCallback<String>() {
                    @Override
                    public boolean onCache(String s) {
                        return false;
                    }

                    @Override
                    public void onSuccess(String s) {
                        Toast.makeText(HttpActivity.this, s, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable throwable, boolean b) {
                        Toast.makeText(HttpActivity.this, "网络请求不成功，请检查网络", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(CancelledException e) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
                break;
            case R.id.bt_http_post_upload:
                RequestParams postFileParams = new RequestParams(NetConfig.POST_FILE);
                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"aa.jpg";
                File file = new File(filePath);
                if (!file.exists()){
                    Toast.makeText(HttpActivity.this, "文件不存在", Toast.LENGTH_SHORT).show();
                    return;
                }
                postFileParams.addBodyParameter("file",file);
                postFileParams.addBodyParameter("username","xiexiaobo");
                x.http().post(postFileParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Toast.makeText(HttpActivity.this, "文件上传成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable throwable, boolean b) {
                        Toast.makeText(HttpActivity.this, "文件上不成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(CancelledException e) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 很多时候 在页面发出网络请求的时候，在onDestroy里面最好取消一下 ： 防止报错  减少电量  减少流量
        if (mCancelable!=null&&!mCancelable.isCancelled()){
            mCancelable.cancel();
        }

        if (mPostCancleable!=null&&!mPostCancleable.isCancelled()){
            mPostCancleable.cancel();
        }
    }
}
