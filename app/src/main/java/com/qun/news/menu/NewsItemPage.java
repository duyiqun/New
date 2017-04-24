package com.qun.news.menu;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.qun.news.Home.BasePage;
import com.qun.news.utils.HMAPI;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Qun on 2017/4/24.
 */

public class NewsItemPage extends BasePage {

    private final String mUrl;//对应频道的接口地址

    public NewsItemPage(Context context, String url) {
        super(context);
        this.mUrl = url;
    }

    @Override
    public View initView() {
        ListView lv = new ListView(mContext);
        return lv;
    }

    @Override
    public void initData() {
        Request request = new Request.Builder().url(HMAPI.BASE_URL + this.mUrl).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                System.out.println(json);
            }
        });
    }
}
