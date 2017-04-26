package com.qun.news.Home;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.qun.news.R;
import com.qun.news.act.HomeActivity;
import com.qun.news.bean.NewsCenterBean;
import com.qun.news.fragment.MenuFragment2;
import com.qun.news.menu.ActionPage;
import com.qun.news.menu.NewsPage;
import com.qun.news.menu.PicPage;
import com.qun.news.menu.TopicPage;
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

    private int index;//记录新闻中心当前显示的界面索引
    private List<BasePage> mNewsCenterPages;
    private FrameLayout mNewsCenterFl;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //默认添加新闻界面进行显示
//            mNews_center_fl.addView(mNewscenterPages.get(0).getRootView());
            switchView(0);

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
        View view = View.inflate(mContext, R.layout.news_center_frame, null);
        mNewsCenterFl = (FrameLayout) view.findViewById(R.id.news_center_fl);
        initTitleBar(view);
        return view;
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

    private List<String> menuTitles = new ArrayList<>();//新闻中心左边的菜单列表标题数据

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

        //创建出新闻中心的4个页面
        mNewsCenterPages = new ArrayList<>();
        mNewsCenterPages.add(new NewsPage(mContext, newsCenterBean.getData().get(0)));//新闻
        mNewsCenterPages.add(new TopicPage(mContext));//专题
        mNewsCenterPages.add(new PicPage(mContext));//组图
        mNewsCenterPages.add(new ActionPage(mContext));//互动

        mHandler.sendEmptyMessage(0);
    }

    //左边菜单界面控制新闻中心进行切换方法
    public void switchView(int position) {
        index = position;
        Toast.makeText(mContext, "新闻中心界面切换了" + position, Toast.LENGTH_SHORT).show();
        BasePage basePage = mNewsCenterPages.get(position);
        //添加view之前先清空所有界面，避免界面叠加
        mNewsCenterFl.removeAllViews();
        mNewsCenterFl.addView(basePage.getRootView());

        mTxt_title.setText(menuTitles.get(position));

        basePage.initData();
        basePage.onResume();
    }

    @Override
    public void onResume() {
        super.onResume();
        //先判断一下新闻中心中，哪一个界面正在显示（有4个页面可能会展示新闻，专题，主图，互动）
        if (mNewsCenterPages != null && mNewsCenterPages.size() > 0) {
            mNewsCenterPages.get(index).onResume();
        }
    }

//    public void initData() {
//        System.out.println("获取了新闻中心的数据");
//    }
}
