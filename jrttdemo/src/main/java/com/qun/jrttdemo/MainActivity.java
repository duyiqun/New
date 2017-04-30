package com.qun.jrttdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView mLv;
    private String url = "http://flv2.bn.netease.com/videolib3/1604/28/fVobI0704/SD/fVobI0704-mobile.mp4";
    private ListItemView currentItemView;//点击后 正在播放视频的条目对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mLv = (ListView) findViewById(R.id.lv);
        mLv.setAdapter(new MyAdapter());
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                final ListItemView listItemView = new ListItemView(MainActivity.this);
                listItemView.getIvPlay().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (currentItemView != null) {
                            currentItemView.release();
                        }

                        //点击播放按钮，添加一个播放视频的界面进行展示即可
                        MyVideoView myVideoView = new MyVideoView(MainActivity.this);
                        myVideoView.setUrl(url);
                        myVideoView.startPlay();
                        listItemView.addVideoView(myVideoView);
                        currentItemView = listItemView;
                    }
                });
                convertView = listItemView;
            } else {
                //将正在播放的视频停止
                if (currentItemView != null && currentItemView == convertView) {//判断当前播放视频的对象与正在复用的对象是否一致，如果一致才进行释放操作
                    currentItemView.release();
                }
            }
            return convertView;
        }
    }
}
