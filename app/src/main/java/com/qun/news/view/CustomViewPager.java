package com.qun.news.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

/**
 * 用来实现一个类似launcher界面效果自定义控件
 * Created by Qun on 2017/4/21.
 */

public class CustomViewPager extends ViewPager {

    private static final float MIN_SCALE = 0.75f;
    private View leftView;//A页面
    private View rightView;//B页面
    private Map<Integer, ImageView> childViews = new HashMap<>();//管理viewpager中界面

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //当适配器初始化方法调用时，将界面保存在控件
    public void addChildView(ImageView imageView, int position) {
        childViews.put(position, imageView);
    }

    //当适配器销毁方法调用时，将界面从集合中删除即可
    public void removeChildView(int position) {
        childViews.remove(position);
    }

    /**
     * 当页面滑动时，该方法实时被调用
     *
     * @param position     当前界面的索引
     * @param offset       viewpager滑动过界面所占的比例值（0,1）
     * @param offsetPixels viewpager滑动过界面像素值
     */
    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels) {
        super.onPageScrolled(position, offset, offsetPixels);
        System.out.println("position = [" + position + "], offset = [" + offset + "], offsetPixels = [" + offsetPixels + "]");
        //当界面滑动时，获取AB页面设置不同的动画
        leftView = childViews.get(position);
        rightView = childViews.get(position + 1);

        startLauncherAnimation(position, offset, offsetPixels);
    }

    private void startLauncherAnimation(int position, float offset, int offsetPixels) {
        //给B页面设置移动与缩放动画
        if (rightView != null) {

//            //        B页面position的取值[1,0]
//
//            //让B页面根据position的变化，动态给B页面设置x轴的平移
//            // Counteract the default slide transition
//            //平移的取值范围[-320,0]
//            view.setTranslationX(pageWidth * -position);//让B页面根据position的变化，动态的设置x的移动，保证B页面始终都显示在屏幕的正中间
//
//            // Scale the page down (between MIN_SCALE and 1)
//            //设置B页面的缩放操作
//            float scaleFactor = MIN_SCALE
//                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
//            //0.75+0.25*0 = 0.75
//            //0.75+0.25*1 = 1
//            //[0.75,1]
//            view.setScaleX(scaleFactor);
//            view.setScaleY(scaleFactor);

            rightView.setTranslationX(-(rightView.getWidth()-offsetPixels));
            //让B页面根据position的变化，动态的设置x的移动，保证B页面始终都显示在屏幕的正中间

            //offset(0,1)
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) *  Math.abs(offset);
            //
            //0.5+0.5*0 = 0.5
            //0.5+0.5*1 = 1
            rightView.setScaleX(scaleFactor);
            rightView.setScaleY(scaleFactor);

            if (leftView != null) {
                leftView.bringToFront();//让界面显示最上层
            }
        }
    }
}
