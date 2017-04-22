package com.qun.news.act;

import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.qun.news.R;
import com.qun.news.fragment.HomeFragment;
import com.qun.news.fragment.MenuFragment;

public class HomeActivity extends SlidingFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置内容界面布局
        setContentView(R.layout.activity_home);

        //设置左边菜单界面布局
        setBehindContentView(R.layout.activity_menu);
        SlidingMenu slidingMenu = getSlidingMenu();//获取侧滑菜单对象
        /**
         * 设置侧滑菜单的显示模式
         * SlidingMenu.LEFT：菜单显示在左边
         * SlidingMenu.RIGHT：菜单显示在右边
         * SlidingMenu.LEFT_RIGHT：菜单左右都显示
         */
        slidingMenu.setMode(SlidingMenu.LEFT);
        /**
         * 设置侧滑菜单的触摸模式
         * SlidingMenu.TOUCHMODE_FULLSCREEN：全屏模式
         * SlidingMenu.TOUCHMODE_MARGIN：边缘模式
         * SlidingMenu.TOUCHMODE_NONE：禁止模式
         */
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置菜单界面显示之后，内容界面显示的宽度
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_content_width);
        //设置菜单界面显示之后的阴影效果
        slidingMenu.setShadowDrawable(R.drawable.shadow);
        //设置阴影的宽度
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);

//        //如果设置为左右都显示，必须设置第二个菜单界面
//        slidingMenu.setSecondaryMenu(R.layout.activity_menu);
//
//        //设置第二个菜单的阴影图片
//        slidingMenu.setSecondaryShadowDrawable(R.drawable.shadow);

        //设置左边菜单界面
        MenuFragment menuFragment = new MenuFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_menu, menuFragment).commit();

        //设置右边菜单界面
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, homeFragment, "HOME").commit();
    }
}
