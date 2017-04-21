package com.qun.news.act;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qun.news.R;
import com.qun.news.view.DepthPageTransformer;
import com.qun.news.view.ZoomOutPageTransformer;

public class GuideActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private int[] imageResIds = {R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        GuideAdapter guideAdapter = new GuideAdapter();
        mViewPager.setAdapter(guideAdapter);
//        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
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
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }
}
