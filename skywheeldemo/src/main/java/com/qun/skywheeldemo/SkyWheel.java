package com.qun.skywheeldemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Qun on 2017/5/1.
 */

public class SkyWheel extends RelativeLayout {

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
}
