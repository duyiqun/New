package com.qun.news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.qun.news.act.HomeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qun on 2017/4/22.
 */

public class MenuFragment extends Fragment implements AdapterView.OnItemClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ListView lv = new ListView(getActivity());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, initData());
        lv.setOnItemClickListener(this);
        lv.setAdapter(adapter);
        return lv;
    }

    private List<String> initData() {
        List<String> datas = new ArrayList<>();
        datas.add("页面一");
        datas.add("页面二");
        datas.add("页面三");
        return datas;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //根据点击的索引创建出对应fragment，并传递给内容界面进行展示
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new Fragment1();
                break;
            case 1:
                fragment = new Fragment2();
                break;
            case 2:
                fragment = new Fragment3();
                break;
            default:
                break;
        }

//        HomeActivity homeActivity = new HomeActivity();
//        homeActivity.switchFragment(fragment);
        if (getActivity() instanceof HomeActivity) {
            ((HomeActivity) getActivity()).switchFragment(fragment);
        }
    }
}
