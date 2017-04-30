package com.qun.jrttdemo;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 专门用来显示视频列表的布局的
 * Created by Qun on 2017/4/30.
 */

class ListItemView extends FrameLayout{

    public ListItemView(@NonNull Context context) {
        this(context, null);
    }

    public ListItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListItemView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ListItemView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }

    private void init() {
        //将布局界面添加到帧布局中显示即可
        View view  = View.inflate(getContext(),R.layout.list_item,null);
        addView(view);
    }
}
