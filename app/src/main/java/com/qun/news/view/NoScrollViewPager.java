package com.qun.news.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Qun on 2017/4/22.
 */

public class NoScrollViewPager extends ViewPager {

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        return super.onTouchEvent(ev);//将触摸事件传递给viewpager，就能够进行左右滑动
        return false;//设置为不处理，并不消费（将触摸事件不传递给viewpager的onTouchEvent方法，viewpager就不会滑动了）
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return super.onInterceptTouchEvent(ev);//有可能拦截，子类中如果有listview，viewpager，scrollview，这些子控件的触摸效果都会失效
        return false;//设置为不拦截，子类中如果有listview，viewpager，scrollview，这些子控件的触摸效果都存在
    }
}
