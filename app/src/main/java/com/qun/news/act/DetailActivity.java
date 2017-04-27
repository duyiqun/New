package com.qun.news.act;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.qun.news.R;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView mWebView;
    private View loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_news_detail);

        initView();
        initData();
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.news_detail_wv);
        //初始化标题
        ImageButton imgbtn_left = (ImageButton) findViewById(R.id.imgbtn_left);
        imgbtn_left.setImageResource(R.mipmap.back);
        imgbtn_left.setOnClickListener(this);
        ImageButton imgbtn_right = (ImageButton) findViewById(R.id.imgbtn_right);
        imgbtn_right.setImageResource(R.mipmap.icon_textsize);
        imgbtn_right.setOnClickListener(this);
        ImageButton btn_right = (ImageButton) findViewById(R.id.btn_right);
        btn_right.setImageResource(R.mipmap.icon_share);
        btn_right.setOnClickListener(this);

        loadingView = findViewById(R.id.loading_view);
    }

    private void initData() {
        String url = getIntent().getStringExtra("url");
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient() {
            //网页加载前操作
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            //网页加载完成后的操作
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loadingView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgbtn_left:
                finish();
                break;
            case R.id.imgbtn_right://字体设置
                mWebView.getSettings().setTextSize(WebSettings.TextSize.LARGEST);
                break;
            case R.id.btn_right://分享
                break;
        }
    }
}
