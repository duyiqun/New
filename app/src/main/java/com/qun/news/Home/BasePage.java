package com.qun.news.Home;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.qun.news.R;
import com.qun.news.act.HomeActivity;

/**
 * Created by Qun on 2017/4/22.
 */

public abstract class BasePage {

    public Context mContext;
    private View mView;
    public boolean isLoad = false;//用来记录每个页面是否加载过数据
    public TextView mTxt_title;//标题控件由子类来设置
    private View mImgbtn_right;
    public SlidingMenu mSlidingMenu;


    public BasePage(Context context) {
        this.mContext = context;
        mView = initView();
        mSlidingMenu = ((HomeActivity) mContext).getMenuFragment().mSlidingMenu;
    }

    public abstract View initView();

    public View getRootView() {
        return mView;
    }

    public abstract void initData();

    public void initTitleBar(View view) {
        ImageButton imgbtn_left = (ImageButton) view.findViewById(R.id.imgbtn_left);
        imgbtn_left.setImageResource(R.mipmap.img_menu);
        imgbtn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlidingMenu.toggle();
            }
        });

        mTxt_title = (TextView) view.findViewById(R.id.txt_title);
        mImgbtn_right = view.findViewById(R.id.imgbtn_right);
        mImgbtn_right.setVisibility(View.GONE);
    }
}
