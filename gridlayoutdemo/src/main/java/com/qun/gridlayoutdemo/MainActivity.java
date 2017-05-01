package com.qun.gridlayoutdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private GridLayout mGridLayout;
    private int index;
    private View.OnLongClickListener olcl = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            //将当前长按的tv显示阴影
            /**
             * 参数一：用来传递数据的对象，不需要给null即可
             * 参数二:用来构造阴影的对象，必须
             * 参数三：本地数据，不需要给null即可
             * 参数四：标记，未定义，给0即可
             */
            v.startDrag(null, new View.DragShadowBuilder(v), null, 0);
//            v.startDragAndDrop(null, new View.DragShadowBuilder(v), null, 0);
            return false;
        }
    };
    private View.OnDragListener odl = new View.OnDragListener() {
        /**
         * DragEvent.ACTION_DRAG_STARTED: 当拖拽事件开始时，执行一次
         * DragEvent.ACTION_DRAG_ENDED：当拖拽事件结束时，手指松开时，执行一次
         * DragEvent.ACTION_DRAG_ENTERED：当拖拽阴影进入设置了监听拖拽事件控件范围内的瞬间执行一次
         * DragEvent.ACTION_DRAG_EXITED：当拖拽阴影离开设置了监听拖拽事件控件范围内的瞬间执行一次
         * DragEvent.ACTION_DRAG_LOCATION：当拖拽阴影在设置了监听拖拽事件控件范围内移动时，实时执行N次
         * DragEvent.ACTION_DROP：当拖拽阴影在设置了监听拖拽事件控件范围内，手指松开时，执行一次
         *
         *
         * @param v 当前接收到拖拽事件的视图（当前v就是mGridLayout）
         * @param event 拖拽事件
         * @return
         */
        @Override
        public boolean onDrag(View v, DragEvent event) {
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mGridLayout = (GridLayout) findViewById(R.id.gridLayout);
        mGridLayout.setOnDragListener(odl);
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

        tv.setOnLongClickListener(olcl);
        mGridLayout.addView(tv, 0);
    }
}
