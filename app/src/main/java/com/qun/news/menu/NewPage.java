package com.qun.news.menu;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.qun.news.Home.BasePage;

/**
 * Created by Qun on 2017/4/23.
 */

public class NewPage extends BasePage {

    public NewPage(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        TextView tv = new TextView(mContext);
        tv.setText("新闻");
        return tv;
    }

    @Override
    public void initData() {

    }
}
