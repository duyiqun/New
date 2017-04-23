package com.qun.news.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.qun.news.Home.NewsCenterPage;
import com.qun.news.R;
import com.qun.news.act.HomeActivity;
import com.qun.news.adapter.MenuAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qun on 2017/4/23.
 */

public class MenuFragment2 extends BaseFragment implements AdapterView.OnItemClickListener {

    private ListView mLvMenuNewsCenter;//新闻中心
    private ListView mLvMenuSmartService;//智慧服务
    private ListView mLvMenuGovaffairs;//政务指南
    private MenuAdapter mNewsAdapter;

    public static final int TYPE_NEWS_CENTER = 0;//新闻中心的类型
    public static final int TYPE_SMART_SERVICE = 1;//新闻中心的类型
    public static final int TYPE_GOVER = 2;//新闻中心的类型

    private int type = TYPE_NEWS_CENTER;

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

                //根据点击的索引控制新闻中心进行界面切换
//                NewsCenterPage newsCenterPage = new NewsCenterPage(mContext);
                NewsCenterPage newsCenterPage = ((HomeActivity) mContext).getHomeFragment().getNewsCenterPage();
                newsCenterPage.switchView(position);
                break;
            case R.id.lv_menu_smart_service:

                break;
            case R.id.lv_menu_govaffairs:

                break;
            default:
                break;
        }
    }

    public void setSmartTitles() {
        //隐藏其他listview，显示智慧服务的listview并设置适配器即可

    }

    //根据对应的类型，显示并设置数据即可
    public void setMenuType(int type) {
        mLvMenuNewsCenter.setVisibility(View.GONE);
        mLvMenuSmartService.setVisibility(View.GONE);
        mLvMenuGovaffairs.setVisibility(View.GONE);
        this.type = type;
        switch (this.type) {
            case TYPE_NEWS_CENTER:
                mLvMenuNewsCenter.setVisibility(View.VISIBLE);
                break;
            case TYPE_SMART_SERVICE:
                mLvMenuSmartService.setVisibility(View.VISIBLE);
                List<String> datas = new ArrayList<>();
                datas.add("智慧服务1");
                datas.add("智慧服务2");
                datas.add("智慧服务3");
                MenuAdapter smartAdapter = new MenuAdapter(mContext, datas);
                mLvMenuSmartService.setAdapter(smartAdapter);
                break;
            case TYPE_GOVER:
                mLvMenuGovaffairs.setVisibility(View.VISIBLE);
                List<String> datas1 = new ArrayList<>();
                datas1.add("政务指南1");
                datas1.add("政务指南2");
                datas1.add("政务指南3");
                MenuAdapter goverAdapter = new MenuAdapter(mContext, datas1);
                mLvMenuGovaffairs.setAdapter(goverAdapter);
                break;
            default:
                break;
        }
    }
}
