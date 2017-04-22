package com.qun.news.Home;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qun on 2017/4/22.
 */

public class SettingPage extends BasePage {

    public SettingPage(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        ListView lv = new ListView(mContext);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, android.R.id.text1, initDatas());
        lv.setAdapter(adapter);
        return lv;
    }

    private List<String> initDatas() {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add("" + i);
        }
        return datas;
    }
}
