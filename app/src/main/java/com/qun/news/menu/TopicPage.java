package com.qun.news.menu;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.qun.news.Home.BasePage;

/**
 * Created by Qun on 2017/4/23.
 */

public class TopicPage extends BasePage {

    public TopicPage(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        TextView tv = new TextView(mContext);
        tv.setText("专题");
        return tv;
    }

    @Override
    public void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(mContext, "专题界面重新显示了", Toast.LENGTH_SHORT).show();
    }
}
