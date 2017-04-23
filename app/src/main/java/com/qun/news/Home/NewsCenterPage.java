package com.qun.news.Home;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Qun on 2017/4/22.
 */

public class NewsCenterPage extends BasePage {

    public NewsCenterPage(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        TextView tv = new TextView(mContext);
        tv.setText("新闻中心");
        return tv;
    }

    @Override
    public void initData() {
        System.out.println("获取了新闻中心的数据");
    }

//    public void initData() {
//        System.out.println("获取了新闻中心的数据");
//    }
}
