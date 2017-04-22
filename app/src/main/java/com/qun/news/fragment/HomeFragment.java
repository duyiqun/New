package com.qun.news.fragment;

import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.qun.news.Home.BasePage;
import com.qun.news.Home.FunctionPage;
import com.qun.news.Home.GoverPage;
import com.qun.news.Home.NewsCenterPage;
import com.qun.news.Home.SettingPage;
import com.qun.news.Home.SmartServicePage;
import com.qun.news.R;
import com.qun.news.adapter.HomeAdapter;
import com.qun.news.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qun on 2017/4/22.
 */

public class HomeFragment extends BaseFragment {

    private NoScrollViewPager mViewPager;
//    private Context mContext;

    private RadioGroup mMainRadio;
//    private SlidingMenu mSlidingMenu;

//    //初始化方法
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mContext = getActivity();
//        mSlidingMenu = ((HomeActivity) mContext).getSlidingMenu();
//    }

//    //界面初始化方法
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.frag_home, null);
//        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
//        mMainRadio = (RadioGroup) view.findViewById(R.id.main_radio);
//        return view;
//    }

    @Override
    public View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.frag_home, null);
        mViewPager = (NoScrollViewPager) view.findViewById(R.id.view_pager);
        mMainRadio = (RadioGroup) view.findViewById(R.id.main_radio);
        return view;
    }

//    //数据初始化方法
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        List<BasePage> homePages = new ArrayList<>();
//        homePages.add(new FunctionPage(mContext));
//        homePages.add(new NewsCenterPage(mContext));
//        homePages.add(new SmartServicePage(mContext));
//        homePages.add(new GoverPage(mContext));
//        homePages.add(new SettingPage(mContext));
//        HomeAdapter homeAdapter = new HomeAdapter(mContext, homePages);
//        mViewPager.setAdapter(homeAdapter);
//
//        //给单选按钮组设置选中监听
//        mMainRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//                //根据选中id切换viewpager界面
//                switch (checkedId) {
//                    case R.id.rb_function://首页
//                        mViewPager.setCurrentItem(0, false);//设置viewpager页面切换时没有滑动效果
//                        //设置侧滑菜单为禁止模式
//                        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
//                        break;
//                    case R.id.rb_news_center://新闻中心
//                        mViewPager.setCurrentItem(1, false);//设置viewpager页面切换时没有滑动效果
//                        //设置侧滑菜单为全屏模式
//                        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//                        break;
//                    case R.id.rb_smart_service://智慧服务
//                        mViewPager.setCurrentItem(2, false);//设置viewpager页面切换时没有滑动效果
//                        //设置侧滑菜单为全屏模式
//                        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//                        break;
//                    case R.id.rb_gov_affairs://政务指南
//                        mViewPager.setCurrentItem(3, false);//设置viewpager页面切换时没有滑动效果
//                        //设置侧滑菜单为全屏模式
//                        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//                        break;
//                    case R.id.rb_setting://设置
//                        mViewPager.setCurrentItem(4, false);//设置viewpager页面切换时没有滑动效果
//                        //设置侧滑菜单为禁止模式
//                        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
//                        break;
//                    default:
//                        break;
//                }
//            }
//        });
//
//        //第一次进入默认选中首页
//        mMainRadio.check(R.id.rb_function);
//    }

    @Override
    protected void initData() {
        List<BasePage> homePages = new ArrayList<>();
        homePages.add(new FunctionPage(mContext));
        homePages.add(new NewsCenterPage(mContext));
        homePages.add(new SmartServicePage(mContext));
        homePages.add(new GoverPage(mContext));
        homePages.add(new SettingPage(mContext));
        HomeAdapter homeAdapter = new HomeAdapter(mContext, homePages);
        mViewPager.setAdapter(homeAdapter);

        //给单选按钮组设置选中监听
        mMainRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                //根据选中id切换viewpager界面
                switch (checkedId) {
                    case R.id.rb_function://首页
                        mViewPager.setCurrentItem(0, false);//设置viewpager页面切换时没有滑动效果
                        //设置侧滑菜单为禁止模式
                        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                        break;
                    case R.id.rb_news_center://新闻中心
                        mViewPager.setCurrentItem(1, false);//设置viewpager页面切换时没有滑动效果
                        //设置侧滑菜单为全屏模式
                        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                        break;
                    case R.id.rb_smart_service://智慧服务
                        mViewPager.setCurrentItem(2, false);//设置viewpager页面切换时没有滑动效果
                        //设置侧滑菜单为全屏模式
                        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                        break;
                    case R.id.rb_gov_affairs://政务指南
                        mViewPager.setCurrentItem(3, false);//设置viewpager页面切换时没有滑动效果
                        //设置侧滑菜单为全屏模式
                        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                        break;
                    case R.id.rb_setting://设置
                        mViewPager.setCurrentItem(4, false);//设置viewpager页面切换时没有滑动效果
                        //设置侧滑菜单为禁止模式
                        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                        break;
                    default:
                        break;
                }
            }
        });

        //第一次进入默认选中首页
        mMainRadio.check(R.id.rb_function);
    }
}
