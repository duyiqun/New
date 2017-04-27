package com.qun.webviewdemo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    private WebSettings mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.webView);
        mSettings = mWebView.getSettings();
        mSettings.setJavaScriptEnabled(true);
        mSettings.setBuiltInZoomControls(true);// 显示缩放按钮(wap网页不支持)
        mSettings.setUseWideViewPort(true);// 支持双击缩放(wap网页不支持)
        mWebView.loadUrl("http://www.qq.com/");

        //让网页跳转在应用内部进行打开
        mWebView.setWebViewClient(new WebViewClient(){
            //网页加载前
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            //网页加载后
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient(){
            //网页的加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                System.out.println("newProgress: " + newProgress);
            }

            //接收网页标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                System.out.println("title: " + title);
            }
        });
    }
}
