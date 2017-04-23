package com.qun.news.Home;

import android.content.Context;
import android.view.View;

/**
 * Created by Qun on 2017/4/22.
 */

public abstract class BasePage {

    public Context mContext;
    private View mView;
    public boolean isLoad = false;//用来记录每个页面是否加载过数据

    public BasePage(Context context) {
        this.mContext = context;
        mView = initView();
    }

    public abstract View initView();

    public View getRootView() {
        return mView;
    }

    public abstract void initData();
}
