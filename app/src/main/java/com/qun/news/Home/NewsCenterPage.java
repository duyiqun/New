package com.qun.news.Home;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.qun.news.utils.HMAPI;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Qun on 2017/4/22.
 */

public class NewsCenterPage extends BasePage {

    public NewsCenterPage(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        TextView tv = new TextView(mContext);
        tv.setText("新闻中心");
        return tv;
    }

    @Override
    public void initData() {
        System.out.println("获取了新闻中心的数据");
        getNetData();
    }

    private void getNetData() {
        Request request = new Request.Builder().url(HMAPI.NEW_CENTER).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            //服务器请求失败
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e);
            }

            //服务器请求成
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                System.out.println(json);
            }
        });
    }

//    public void initData() {
//        System.out.println("获取了新闻中心的数据");
//    }
}
