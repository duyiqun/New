package com.qun.news.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Qun on 2017/4/23.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {

    public Context mContext;
    public List<T> mDatas;

    public MyBaseAdapter(Context context, List<T> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
