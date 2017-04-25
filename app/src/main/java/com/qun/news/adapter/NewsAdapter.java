package com.qun.news.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.qun.news.Home.BasePage;

import java.util.List;

/**
 * Created by Qun on 2017/4/24.
 */

public class NewsAdapter extends PagerAdapter {

    private final Context mContext;
    private final List<BasePage> mDatas;
    private final List<String> mTitles;

    public NewsAdapter(Context context, List<BasePage> datas, List<String> newsTitles) {
        this.mContext = context;
        this.mDatas = datas;
        this.mTitles = newsTitles;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mDatas.get(position).getRootView();
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        container.addView(mDatas.get(position).getRootView());
        return mDatas.get(position).getRootView();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
