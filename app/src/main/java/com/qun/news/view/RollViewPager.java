package com.qun.news.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Qun on 2017/4/25.
 */

public class RollViewPager extends ViewPager {

    private TextView titleText;
    private List<String> titles;

    public RollViewPager(Context context) {
        super(context);
    }

    public RollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //动态传递数据并设置默认第一个标题
    public void setTitles(TextView topNewsTitle, List<String> newPagerTitles) {
        this.titleText = topNewsTitle;
        this.titles = newPagerTitles;
        this.titleText.setText(titles.get(0));
    }
}
