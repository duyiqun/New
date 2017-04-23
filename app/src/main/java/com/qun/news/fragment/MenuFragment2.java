package com.qun.news.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.qun.news.R;
import com.qun.news.adapter.MenuAdapter;

import java.util.List;

/**
 * Created by Qun on 2017/4/23.
 */

public class MenuFragment2 extends BaseFragment {

    private ListView mLvMenuNewsCenter;//新闻中心
    private ListView mLvMenuSmartService;//智慧服务
    private ListView mLvMenuGovaffairs;//政务指南
    private MenuAdapter mNewsAdapter;

    @Override
    public View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.layout_left_menu, null);
        mLvMenuNewsCenter = (ListView) view.findViewById(R.id.lv_menu_news_center);
        mLvMenuSmartService = (ListView) view.findViewById(R.id.lv_menu_smart_service);
        mLvMenuGovaffairs = (ListView) view.findViewById(R.id.lv_menu_govaffairs);
        return view;
    }

    @Override
    protected void initData() {

    }

    public void setMenuTitles(List<String> menuTitles) {
        if (mNewsAdapter == null) {
            mNewsAdapter = new MenuAdapter(mContext, menuTitles);
            mLvMenuNewsCenter.setAdapter(mNewsAdapter);
        } else {
            mNewsAdapter.notifyDataSetChanged();
        }
    }
}
