package com.qun.news.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qun.news.R;

import java.util.List;

/**
 * Created by Qun on 2017/4/25.
 */

public class RollViewPager extends ViewPager {

    private TextView titleText;
    private List<String> titles;
    private List<String> images;
    private RollAdapter mRollAdapter;
    private List<ImageView> mDots;
    private int previousPosition;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int currentItem = RollViewPager.this.getCurrentItem();
            RollViewPager.this.setCurrentItem((currentItem + 1) % titles.size(), false);

            mHandler.sendEmptyMessageDelayed(0, 2000);
        }
    };
    private int mStartX;

    public RollViewPager(Context context) {
        this(context, null);
    }

    public RollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        this.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //点变化，标题变化
                titleText.setText(titles.get(position));
                mDots.get(previousPosition).setImageResource(R.mipmap.dot_normal);
                previousPosition = position;
                mDots.get(position).setImageResource(R.mipmap.dot_focus);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mHandler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_UP:
                        mHandler.sendEmptyMessageDelayed(0, 2000);
                        System.out.println("MotionEvent.ACTION_UP");
                        break;
                    case MotionEvent.ACTION_CANCEL://当触摸点离开控件或者，触摸事件被拦截时，cancel事件会执行，它一执行，up事件就不会执行了
                        System.out.println("MotionEvent.ACTION_CANCEL");
                        mHandler.sendEmptyMessageDelayed(0, 2000);
                        break;
                    default:
                        break;
                }

                return false;
            }
        });
    }

    //动态传递数据并设置默认第一个标题
    public void setTitles(TextView topNewsTitle, List<String> newPagerTitles) {
        this.titleText = topNewsTitle;
        this.titles = newPagerTitles;
        this.titleText.setText(titles.get(0));
    }

    public void setImages(List<String> newsPagerImages) {
        this.images = newsPagerImages;
    }

    public void start() {
        //设置适配器即可
        if (mRollAdapter == null) {
            mRollAdapter = new RollAdapter();
            this.setAdapter(mRollAdapter);
        } else {
            mRollAdapter.notifyDataSetChanged();
        }

        mHandler.sendEmptyMessageDelayed(0, 2000);
    }

    public void setDots(List<ImageView> dots) {
        this.mDots = dots;
    }

    private class RollAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = new ImageView(getContext());
            iv.setScaleType(ImageView.ScaleType.FIT_XY);//glide默认使用src设置图片，设置缩放模式为fitxy即可
            Glide.with(getContext()).load(images.get(position)).into(iv);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    //事件分发
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        requestDisallowInterceptTouchEvent(true);//请求rollviewpager的父控件不拦截触摸事件
//        requestDisallowInterceptTouchEvent(false);//不请求rollviewpager的父控件不拦截触摸事件，（父控件默认操作，想拦截就拦截）

//        //如果当前的索引不是0
//        if (this.getCurrentItem() != 0) {
//            requestDisallowInterceptTouchEvent(true);
//        }

        //如果当前的索引不是0,并且是右滑时才请求父控件不拦截
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = (int) ev.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                int endX = (int) ev.getRawX();
                int diffX = endX - mStartX;
                if (diffX > 0 && this.getCurrentItem() != 0) {
                    requestDisallowInterceptTouchEvent(true);
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
