package com.qun.gridlayoutdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private GridLayout mGridLayout;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mGridLayout = (GridLayout) findViewById(R.id.gridLayout);

    }

    public void addItem(View view) {
        //往mGridLayout添加条目
        TextView tv = new TextView(MainActivity.this);
        tv.setText(index + "");
        tv.setBackgroundResource(R.drawable.sharp_tv_normal);
        index++;
//        mGridLayout.addView(tv);
        int margin = 5;//5个像素
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels / 4 - 2 * margin;
        layoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
        tv.setLayoutParams(layoutParams);
        layoutParams.setMargins(margin, margin, margin, margin);
        tv.setGravity(Gravity.CENTER);
        mGridLayout.addView(tv, 0);
    }
}
