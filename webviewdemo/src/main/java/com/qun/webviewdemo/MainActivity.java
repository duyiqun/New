package com.qun.webviewdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    private WebSettings mSettings;
    private long quitTime;

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
//        mWebView.loadUrl("http://www.baidu.com/");

        //加载本地assets目录下的网页
        mWebView.loadUrl("file:///android_asset/demo.html");

        //让网页跳转在应用内部进行打开
        mWebView.setWebViewClient(new WebViewClient() {
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
        mWebView.setWebChromeClient(new WebChromeClient() {
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

        initData();
    }

    //js与android代码互调
    private void initData() {
        /**
         * 参数一：接口对象
         * 参数二：接口对象名称(1.对象名称必修与js对象名称一致)
         */
        mWebView.addJavascriptInterface(new JsCallAndroid() {
            @JavascriptInterface//3.在4.1以上版本一定要加该注解
            @Override
            public void onCallback() {
                Toast.makeText(MainActivity.this, "js代码调用了android代码", Toast.LENGTH_SHORT).show();
            }
        }, "demo");
    }

    public interface JsCallAndroid {
        void onCallback();//2.接口方法必须与js中方法名与方法参数保持一致
    }

    public void aCallJ(View view) {
        mWebView.loadUrl("javascript:wave()");
    }

    public void before(View view) {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            Toast.makeText(this, "已经是第一页了", Toast.LENGTH_SHORT).show();
        }
    }

    public void next(View view) {
        if (mWebView.canGoForward()) {
            mWebView.goForward();
        } else {
            Toast.makeText(this, "已经是最后一页了", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            if (System.currentTimeMillis() - quitTime < 2000) {
                finish();
            } else {
                Toast.makeText(this, "再点击一次，退出应用", Toast.LENGTH_SHORT).show();
                quitTime = System.currentTimeMillis();
            }
        }
    }
}
