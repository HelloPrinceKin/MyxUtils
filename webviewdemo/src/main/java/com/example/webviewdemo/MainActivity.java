package com.example.webviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView mWeb;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWeb = (WebView) findViewById(R.id.webview);
        url = "https://www.baidu.com";

        aboutWebView();
    }

    private void aboutWebView() {

        WebSettings settings = mWeb.getSettings();
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setMinimumFontSize(settings.getMinimumFontSize() + 8);
        settings.setAllowFileAccess(false);
        settings.setTextSize(WebSettings.TextSize.NORMAL);
        mWeb.setVerticalScrollbarOverlay(true);
        //mWeb.setWebViewClient(new MyWebViewClient());
        mWeb.setWebViewClient(new WebViewClient());
        mWeb.loadUrl(url);
        //mWeb.loadUrl(url);

    }

    @Override
    public void onBackPressed() {
        if(mWeb.canGoBack()){
            mWeb.goBack();
        }else {

            super.onBackPressed();
        }
    }
}
