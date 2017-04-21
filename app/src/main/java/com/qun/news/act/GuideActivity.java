package com.qun.news.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.qun.news.R;
import com.qun.news.view.RotateTransformer;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    //    private CustomViewPager mViewPager;
    private int[] imageResIds = {R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};
    private Button mBtnGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
//        mViewPager = (CustomViewPager) findViewById(R.id.viewPager);
        GuideAdapter guideAdapter = new GuideAdapter();
        mViewPager.setAdapter(guideAdapter);
//        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
//        mViewPager.setPageTransformer(true, new DepthPageTransformer());
        mViewPager.setPageTransformer(true, new RotateTransformer());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             * 当页面滑动时，该方法实时被调用
             * @param position     当前界面的索引
             * @param positionOffset       viewpager滑动过界面所占的比例值（0,1）
             * @param positionOffsetPixels viewpager滑动过界面像素值
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == imageResIds.length - 1) {
                    mBtnGuide.setVisibility(View.VISIBLE);
                } else {
                    mBtnGuide.setVisibility(View.GONE);
                }
            }

            //当页面被 选中时，才调用一次
            @Override
            public void onPageSelected(int position) {
//                if (position == imageResIds.length - 1) {
//                    mBtnGuide.setVisibility(View.VISIBLE);
//                } else {
//                    mBtnGuide.setVisibility(View.GONE);
//                }
            }

            //当页面滚动状态改变时，才执行一次
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBtnGuide = (Button) findViewById(R.id.btn_guide);
        mBtnGuide.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_guide:
                startActivity(new Intent(GuideActivity.this, HomeActivity.class));
                finish();
                break;
            default:
                break;
        }
    }

    private class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageResIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(GuideActivity.this);
//            imageView.setImageResource(imageResIds[position]);//src 图片有多大就显示多大
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setBackgroundResource(imageResIds[position]);
            //如果是最后一页的界面时，可以通过布局文件创建出界面并显示（button直接写在最后一个界面中即可）
            container.addView(imageView);
//            mViewPager.addChildView(imageView, position);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
//            mViewPager.removeChildView(position);
        }
    }
}
