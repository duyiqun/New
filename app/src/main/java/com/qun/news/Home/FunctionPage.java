package com.qun.news.Home;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Qun on 2017/4/22.
 */

public class FunctionPage extends BasePage {

    public FunctionPage(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        TextView tv = new TextView(mContext);
        tv.setText("首页");
        return tv;
    }
}
