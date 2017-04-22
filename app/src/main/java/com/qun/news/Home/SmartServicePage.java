package com.qun.news.Home;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Qun on 2017/4/22.
 */

public class SmartServicePage extends BasePage {

    public SmartServicePage(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        TextView tv = new TextView(mContext);
        tv.setText("智慧服务");
        return tv;    }
}
