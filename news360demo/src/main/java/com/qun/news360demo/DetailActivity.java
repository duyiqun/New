package com.qun.news360demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mWebView = (WebView) findViewById(R.id.webView);
        String url = getIntent().getStringExtra("url");
        mWebView.loadUrl(url);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        //给图片设置点击事件（android调用js代码的方法来实现图片点击事件）
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                addOnClick();
            }
        });

        //当点击图片时，调用android代码进行界面切换（图片点击后通过js代码调用android代码来进行界面跳转）
        mWebView.addJavascriptInterface(new JsCallAndroid() {
            @JavascriptInterface
            @Override
            public void openImage(String imagePath) {
                Toast.makeText(DetailActivity.this, imagePath, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetailActivity.this, ImageDetailActivity.class);
                intent.putExtra("url", imagePath);
                startActivity(intent);
            }
        },"imagelistener");
    }

    public interface JsCallAndroid{
        void openImage(String imagePath);
    }

    //android调用js代码的方法来实现图片点击事件
    private void addOnClick() {
        //android调用js代码
        mWebView.loadUrl("javascript:(function(){"
                + "var objs = document.getElementsByTagName(\"img\"); "
                + "for(var i=0;i<objs.length;i++)  " + "{"
                + "    objs[i].onclick=function()  " + "   " + " {  "
                + "        window.imagelistener.openImage(this.src);  "
                + "    }  " + "}" + "})()");

    }
}
