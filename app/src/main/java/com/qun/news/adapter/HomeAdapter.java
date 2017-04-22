package com.qun.news.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.qun.news.Home.BasePage;

import java.util.List;

/**
 * Created by Qun on 2017/4/22.
 */

public class HomeAdapter extends PagerAdapter {

    private final Context mContext;
    private final List<BasePage> mDatas;

    public HomeAdapter(Context context, List<BasePage> homePages) {
        this.mContext = context;
        this.mDatas = homePages;
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
        container.addView(mDatas.get(position).getRootView());
        return mDatas.get(position).getRootView();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
