package com.qun.skywheeldemo;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Qun on 2017/5/1.
 */

public class SkyWheel extends RelativeLayout {

    private PointF mCenter;//圆心
    private float radius;//半径

    public SkyWheel(Context context) {
        this(context, null);
    }

    public SkyWheel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SkyWheel(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    public SkyWheel(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        compare();
    }

    private void compare() {
        //计算出圆心与半径
        mCenter = new PointF();
        mCenter.x = this.getWidth() / 2;
        mCenter.y = this.getHeight() / 2;
        //先获取子控件中最大的宽与高
        float max_width = 0;
        float max_height = 0;

        for (int i = 0; i < this.getChildCount(); i++) {
            View childview = this.getChildAt(i);
            if (childview.getWidth() > max_width) {
                max_width = childview.getWidth();
            }
            if (childview.getHeight() > max_height) {
                max_height = childview.getHeight();
            }
        }

        float r1 = mCenter.x - max_width / 2;
        float r2 = mCenter.y - max_height / 2;

        radius = Math.min(r1, r2);
    }
}
