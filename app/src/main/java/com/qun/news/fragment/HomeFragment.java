package com.qun.news.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qun.news.Home.BasePage;
import com.qun.news.Home.FunctionPage;
import com.qun.news.Home.GoverPage;
import com.qun.news.Home.NewsCenterPage;
import com.qun.news.Home.SettingPage;
import com.qun.news.Home.SmartServicePage;
import com.qun.news.R;
import com.qun.news.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qun on 2017/4/22.
 */

public class HomeFragment extends Fragment {

    private ViewPager mViewPager;
    private Context mContext;

    //初始化方法
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_home, null);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        return view;
    }

    //数据初始化方法
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<BasePage> homePages = new ArrayList<>();
        homePages.add(new FunctionPage(mContext));
        homePages.add(new NewsCenterPage(mContext));
        homePages.add(new SmartServicePage(mContext));
        homePages.add(new GoverPage(mContext));
        homePages.add(new SettingPage(mContext));
        HomeAdapter homeAdapter = new HomeAdapter(mContext, homePages);
        mViewPager.setAdapter(homeAdapter);
    }
}
