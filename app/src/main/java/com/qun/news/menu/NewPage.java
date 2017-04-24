package com.qun.news.menu;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qun.news.Home.BasePage;
import com.qun.news.R;
import com.qun.news.adapter.NewsAdapter;
import com.qun.news.bean.NewsCenterBean;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qun on 2017/4/23.
 */

public class NewPage extends BasePage {

    private final NewsCenterBean.DataBean mDataBean;
    private ViewPager mPager;
    private TabPageIndicator mIndicator;

    public NewPage(Context context, NewsCenterBean.DataBean dataBean) {
        super(context);
        this.mDataBean = dataBean;
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.simple_tabs, null);
        mPager = (ViewPager) view.findViewById(R.id.pager);
        mIndicator = (TabPageIndicator) view.findViewById(R.id.indicator);
        return view;
    }

    @Override
    public void initData() {
        List<BasePage> newsPages = new ArrayList<>();
        List<String> newsTitles = new ArrayList<>();
        for (NewsCenterBean.DataBean.ChildrenBean childrenBean : mDataBean.getChildren()) {
            newsPages.add(new NewsItemPage(mContext));//viewpager中将要显示的页面对象集合
            newsTitles.add(childrenBean.getTitle());//新闻界面标题数据集合
        }
        NewsAdapter newsAdapter = new NewsAdapter(mContext, newsPages, newsTitles);
        mPager.setAdapter(newsAdapter);
        mIndicator.setViewPager(mPager);
    }
}
