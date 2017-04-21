package com.qun.news.view;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Qun on 2017/4/21.
 */

public class RotateTransformer implements ViewPager.PageTransformer {

    private static final float MAX_ROTATION = 25f;//旋转的最大角度

    @Override
    public void transformPage(View page, float position) {
//        System.out.println("view:" + page);
//        System.out.println("position:" + position);

//        view 被添加到viewpager中的子界面 A页面B页面
//        A页面position的取值[0,-1]
//        B页面position的取值[1,0]


        int pageWidth = page.getWidth();//屏幕宽
        System.out.println("pageWidth:" + pageWidth);

        //根据position的取值范围，区分AB页面并设置不同的动画操作

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            page.setRotation(0);//将最左边的界面设置不旋转

        } else if (position <= 0) { // [-1,0]
            //        A页面position的取值[0,-1]
            // Use the default slide transition when moving to the left page
            //A页面从0旋转到-25，[0,-25]
            page.setPivotX(pageWidth / 2);
            page.setPivotY(page.getHeight());
            page.setRotation(MAX_ROTATION * position);

        } else if (position <= 1) { // (0,1]
            //        B页面position的取值[1,0]
            // Fade the page out.
            //B从25旋转到0，[25,0]
            page.setPivotX(pageWidth / 2);
            page.setPivotY(page.getHeight());
            page.setRotation(MAX_ROTATION * position);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            page.setRotation(0);//设置最右边的界面不旋转
        }
    }
}
