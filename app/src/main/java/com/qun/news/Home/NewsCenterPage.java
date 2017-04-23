package com.qun.news.Home;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.qun.news.act.HomeActivity;
import com.qun.news.bean.NewsCenterBean;
import com.qun.news.fragment.MenuFragment2;
import com.qun.news.utils.GsonTools;
import com.qun.news.utils.HMAPI;
import com.qun.news.utils.SpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Qun on 2017/4/22.
 */

public class NewsCenterPage extends BasePage {

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//        MenuFragment menuFragment = new MenuFragment();
            MenuFragment2 menuFragment = ((HomeActivity) mContext).getMenuFragment();
            menuFragment.setMenuTitles(menuTitles);
        }
    };

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
        //先从本地获取json如果有则展示，每次都获取一次最新的数据
        String json = SpUtil.getString(mContext, HMAPI.NEW_CENTER, "");
        if (!TextUtils.isEmpty(json)) {
            parseGson(json);
        }
        System.out.println("获取了新闻中心的数据");
        System.out.println(json);
        getNetData();
    }

    private void getNetData() {
        Request request = new Request.Builder().url(HMAPI.NEW_CENTER).build();
//        Request request = new Request.Builder().url("http://192.168.0.106:8080/Array_Json.json").build();
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
                parseGson(json);
            }
        });
    }

    List<String> menuTitles = new ArrayList<>();//新闻中心左边的菜单列表标题数据

    private void parseGson(String json) {
        isLoad = true;
        //将新闻中心数据缓存下来（一般使用数据库存数据），简单点使用sp了
        SpUtil.saveString(mContext, HMAPI.NEW_CENTER, json);

//        Gson gson = new Gson();
//        NewsCenterBean newsCenterBean = gson.fromJson(json, NewsCenterBean.class);
//        System.out.println(newsCenterBean);

//        List<ArrayBean> datas = GsonTools.changeGsonToList(json, ArrayBean.class);
//        System.out.println(datas.size());

        NewsCenterBean newsCenterBean = GsonTools.changeGsonToBean(json, NewsCenterBean.class);

        //封装标题数据，传递给左边菜单界面进行显示
        menuTitles.clear();
        for (NewsCenterBean.DataBean dataBean : newsCenterBean.getData()) {
            menuTitles.add(dataBean.getTitle());
        }

        mHandler.sendEmptyMessage(0);
    }

//    public void initData() {
//        System.out.println("获取了新闻中心的数据");
//    }
}
