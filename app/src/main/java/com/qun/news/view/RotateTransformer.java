package com.qun.news.view;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Qun on 2017/4/21.
 */

public class RotateTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.75f;

    @Override
    public void transformPage(View page, float position) {
        System.out.println("view:" + page);
        System.out.println("position:" + position);

//        view 被添加到viewpager中的子界面 A页面B页面
//        A页面position的取值[0,-1]
//        B页面position的取值[1,0]


        int pageWidth = page.getWidth();//屏幕宽
        System.out.println("pageWidth:" + pageWidth);

        //根据position的取值范围，区分AB页面并设置不同的动画操作

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            page.setAlpha(0);//将最左边的界面设置不可见

        } else if (position <= 0) { // [-1,0]
            //        A页面position的取值[0,-1]
            // Use the default slide transition when moving to the left page
            page.setAlpha(1);//设置A页面完全可见
            page.setTranslationX(0);//设置A页面在x轴不移动(为什么A页面移动了？viewpager自带的效果)
            page.setScaleX(1);//设置A页面不缩放
            page.setScaleY(1);

        } else if (position <= 1) { // (0,1]
            //        B页面position的取值[1,0]
            // Fade the page out.
            //设置B页面根据position的变化，动态改变B页面的透明度
            //透明度取值范围[0,1]
            page.setAlpha(1 - position);

            //让B页面根据position的变化，动态给B页面设置x轴的平移
            // Counteract the default slide transition
            //平移的取值范围[-320,0]
            page.setTranslationX(pageWidth * -position);//让B页面根据position的变化，动态的设置x的移动，保证B页面始终都显示在屏幕的正中间

            // Scale the page down (between MIN_SCALE and 1)
            //设置B页面的缩放操作
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            //0.75+0.25*0 = 0.75
            //0.75+0.25*1 = 1
            //[0.75,1]
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            page.setAlpha(0);//设置最右边的界面不可见
        }
    }
}
