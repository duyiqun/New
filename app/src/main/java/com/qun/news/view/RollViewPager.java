package com.qun.news.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
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
}
