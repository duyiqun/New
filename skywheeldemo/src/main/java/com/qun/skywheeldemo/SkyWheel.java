package com.qun.skywheeldemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

/**
 * Created by Qun on 2017/5/1.
 */

public class SkyWheel extends RelativeLayout {

    private PointF mCenter;//圆心
    private float radius;//半径
    double degree;//夹角
    private double diffDegree;//d当前的角度变化
    private GestureDetector mDetector;
    private ValueAnimator mVa;

    public SkyWheel(Context context) {
        this(context, null);
    }

    public SkyWheel(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public SkyWheel(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    public SkyWheel(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }

    private void init() {
        mDetector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
            //当手指按下调用
            @Override
            public boolean onDown(MotionEvent e) {
                if (mVa != null && mVa.isRunning()) {
                    mVa.cancel();
                }
                return false;
            }

            //短暂按压时调用一次
            @Override
            public void onShowPress(MotionEvent e) {

            }

            //点击时调用一次
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                //判断点击的触摸点是否进入了某一个子控件如果进入了直接调用该控件的点击事件
                int clickIndex = getClickIndex(e);
                if (clickIndex > -1) {
                    SkyWheel.this.getChildAt(clickIndex).performClick();//直接强制调用起当前view的点击事件
                }
                return false;
            }

            //当手指触摸滚动时，实时调用

            /**
             *
             * @param e1    第一个按下事件action_down
             * @param e2    当前最近的一个移动事件action_move
             * @param distanceX distanceX = e2的前一个移动事件的x值 - 当前移动事件的x值
             * @param distanceY distanceY = e2的前一个移动事件的y值 - 当前移动事件的y值
             * @return
             */
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                //计算出手指滑动后的角度变化
                //计算出结束点的角度
                double endDegree = getDegree(e2.getY(), e2.getX());
                //计算出起始点的角度
                double startDegree = getDegree(distanceY + e2.getY(), distanceX + e2.getX());

                //计算角度变化
                double diff = endDegree - startDegree;
                setDegree(diffDegree + diff);
                return false;
            }

            //长按时调用
            @Override
            public void onLongPress(MotionEvent e) {

            }

            //飞一般感觉，惯性操作

            /**
             *
             * @param e1 按下事件action_down
             * @param e2    移动事件action_move
             * @param velocityX     当手指松开时的瞬间，在x轴上的移动速度像素/s
             * @param velocityY     当手指松开时的瞬间，在y轴上的移动速度像素/s
             * @return
             */
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                //计算出起始点的角度
                double startDegree = getDegree(e2.getY(), e2.getX());
                //计算出1ms后结束点的角度
                double endDegree = getDegree(e2.getY() + velocityY / 1000, e2.getX() + velocityX / 1000);
                //计算出1ms的角速度
                double degreeAfter1ms = endDegree - startDegree;
                //计算出1s的角速度
                double degreeAfter1s = degreeAfter1ms * 1000;

                startFlingAnimation(degreeAfter1s);

                return false;
            }
        });
    }

    private int getClickIndex(MotionEvent e) {
        //遍历子控件，进行判断
        for (int i = 0; i < this.getChildCount(); i++) {
            View childAt = this.getChildAt(i);
            if (childAt.getLeft() < e.getX() && childAt.getTop() < e.getY() && childAt.getRight() > e.getX() && childAt.getBottom() > e.getY()) {
                return i;
            }
        }
        return -1;
    }

    private void startFlingAnimation(double degreeAfter1s) {
        //根据角速度计算出，动画时间内的角度变化
        int duration = (int) (Math.abs(degreeAfter1s) * 1000);
        if (duration > 1000) {
            duration = 1000;
        }
        //计算出角度变化 = 角速度*事件
        double diff = degreeAfter1s * duration / 1000;
        mVa = ValueAnimator.ofFloat((float) this.diffDegree, (float) (this.diffDegree + diff));
        mVa.setDuration(duration);
        mVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float animatedValue = (Float) animation.getAnimatedValue();
                setDegree(animatedValue);
            }
        });
        //设置为先快后慢即可
        mVa.setInterpolator(new DecelerateInterpolator(2));
        mVa.start();
    }

    //通过反正切函数获取角度
    private double getDegree(float y, float x) {
        return Math.atan2(y - mCenter.y, x - mCenter.x);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        compare();
        //排版每个子控件的位置
        for (int i = 0; i < this.getChildCount(); i++) {
            View childView = this.getChildAt(i);
            childView.layout((int) (mCenter.x + Math.sin(i * degree + diffDegree) * radius - childView.getWidth() / 2), (int) (mCenter.y - Math.cos(i * degree + diffDegree) * radius - childView.getHeight() / 2), (int) (mCenter.x + Math.sin(i * degree + diffDegree) * radius + childView.getWidth() / 2), (int) (mCenter.y - Math.cos(i * degree + diffDegree) * radius + childView.getHeight() / 2));
        }
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
            View childView = this.getChildAt(i);
            if (childView.getWidth() > max_width) {
                max_width = childView.getWidth();
            }
            if (childView.getHeight() > max_height) {
                max_height = childView.getHeight();
            }
        }

        float r1 = mCenter.x - max_width / 2;
        float r2 = mCenter.y - max_height / 2;

        radius = Math.min(r1, r2);
        //计算夹角
        degree = Math.PI * 2 / this.getChildCount();
    }

    public void setDegree(double degree) {
        this.diffDegree = degree;
        requestLayout();//让onlayout重新排版
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        return true;
    }

    //事件拦截
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
