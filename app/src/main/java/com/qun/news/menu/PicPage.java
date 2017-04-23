package com.qun.news.menu;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.qun.news.Home.BasePage;

/**
 * Created by Qun on 2017/4/23.
 */

public class PicPage extends BasePage {

    public PicPage(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        TextView tv = new TextView(mContext);
        tv.setText("组图");
        return tv;
    }

    @Override
    public void initData() {

    }
}
