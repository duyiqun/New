package com.qun.news.menu;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
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

public class NewsPage extends BasePage implements ViewPager.OnPageChangeListener {

    private final NewsCenterBean.DataBean mDataBean;
    private ViewPager mPager;
    private TabPageIndicator mIndicator;
    private int currentItem;//记录当前新闻子条目界面的索引

    public NewsPage(Context context, NewsCenterBean.DataBean dataBean) {
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
        mIndicator.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //只有北京界面时，侧滑菜单才是全屏模式，否则禁止模式
        if (position == 0) {
            mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
        currentItem = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //判断新闻界面重新被显示时，判断当前currentItem是否为0再设置侧滑菜单的触摸模式
    @Override
    public void onResume() {
        super.onResume();
        if (currentItem == 0) {
            mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
        //如果重新删除并添加到帧布局中进行显示时，viewpager默认会显示在第一个界面，但是指针没有动，手动通过setcurrentItem来跳转一下
        mPager.setCurrentItem(currentItem);
    }
}
