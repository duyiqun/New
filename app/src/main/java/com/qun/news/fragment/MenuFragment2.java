package com.qun.news.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.qun.news.R;
import com.qun.news.adapter.MenuAdapter;

import java.util.List;

/**
 * Created by Qun on 2017/4/23.
 */

public class MenuFragment2 extends BaseFragment implements AdapterView.OnItemClickListener {

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
        mLvMenuNewsCenter.setOnItemClickListener(this);
        mLvMenuSmartService.setOnItemClickListener(this);
        mLvMenuGovaffairs.setOnItemClickListener(this);
    }

    public void setMenuTitles(List<String> menuTitles) {
        if (mNewsAdapter == null) {
            mNewsAdapter = new MenuAdapter(mContext, menuTitles);
            mLvMenuNewsCenter.setAdapter(mNewsAdapter);
        } else {
            mNewsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.lv_menu_news_center://新闻中心
                //点击时，记录下对应的点击的索引，让列表进行刷新即可
                mNewsAdapter.setClickPosition(position);
//                mNewsAdapter.notifyDataSetChanged();
                mSlidingMenu.toggle();
                break;
            case R.id.lv_menu_smart_service:

                break;
            case R.id.lv_menu_govaffairs:

                break;
            default:
                break;
        }
    }
}
