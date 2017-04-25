package com.qun.news.menu;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.qun.news.Home.BasePage;
import com.qun.news.R;
import com.qun.news.adapter.NewsItemAdapter;
import com.qun.news.bean.NewsItemBean;
import com.qun.news.utils.DensityUtil;
import com.qun.news.utils.GsonTools;
import com.qun.news.utils.HMAPI;
import com.qun.news.view.RollViewPager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private ListView mLv;
    private NewsItemBean mNewsItemBean;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            //动态创建小圆点
            initDots(mNewsItemBean.getData().getTopnews().size());

            //创建出专门用于显示轮播图效果的控件
            RollViewPager rollViewPager = new RollViewPager(mContext);
            rollViewPager.setTitles(mTopNewsTitle,mNewPagerTitles);
            mLv.addHeaderView(mTopView);
            NewsItemAdapter newsItemAdapter = new NewsItemAdapter(mContext, mNewsItemBean.getData().getNews());
            mLv.setAdapter(newsItemAdapter);
        }
    };
    private LinearLayout mDotsLl;//专门显示小圆点的容器
    private List<ImageView> dots = new ArrayList<>();
    private TextView mTopNewsTitle;//用来显示轮播图的标题

    private void initDots(int size) {
        mDotsLl.removeAllViews();
        dots.clear();
        //将小圆点创建并添加到轮播图中显示
        for (int i = 0; i < size; i++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5));
            ImageView point = new ImageView(mContext);

            if (i == 0) {
                point.setImageResource(R.mipmap.dot_focus);
            } else {
                layoutParams.leftMargin = DensityUtil.dip2px(mContext, 5);
                point.setImageResource(R.mipmap.dot_normal);
            }
            point.setLayoutParams(layoutParams);
            mDotsLl.addView(point);
            dots.add(point);
        }
    }

    private View mTopView;//用于显示轮播图头视图

    public NewsItemPage(Context context, String url) {
        super(context);
        this.mUrl = url;
    }

    @Override
    public View initView() {
//        mLv = new ListView(mContext);
        View view = View.inflate(mContext, R.layout.frag_item_news, null);
        mLv = (ListView) view.findViewById(R.id.lv_item_news);
        //创建头视图view
        mTopView = View.inflate(mContext, R.layout.layout_roll_view, null);

        mDotsLl = (LinearLayout) mTopView.findViewById(R.id.dots_ll);
        mTopNewsTitle = (TextView) mTopView.findViewById(R.id.top_news_title);
        return mLv;
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
                parseJson(json);
            }
        });
    }

    List<String> mNewPagerTitles = new ArrayList<>();

    private void parseJson(String json) {
        mNewsItemBean = GsonTools.changeGsonToBean(json, NewsItemBean.class);

        //封装标题
        mNewPagerTitles.clear();
        for (NewsItemBean.DataBean.TopnewsBean topnewsBean : mNewsItemBean.getData().getTopnews()) {
            mNewPagerTitles.add(topnewsBean.getTitle());
        }

        mHandler.sendEmptyMessage(0);
    }
}
