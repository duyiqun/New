package com.qun.gridlayoutdemo;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridLayout mGridLayout;
    private int index;
    private View dragedView;//被拖拽的视图
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
            dragedView = v;
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
            String dragEventAction = getDragEventAction(event);
            System.out.println(dragEventAction);
//            Rect rect = new Rect();
//            rect.contains();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //创建出与子控件对应的矩形对象
                    initRects();

                    break;
                case DragEvent.ACTION_DRAG_LOCATION:
                    //实时判断触摸点是否进入了某一个子控件，如果进入了，就返回当前子控件的索引
                    int touchIndex = getTouchIndex(event);
                    //判断被拖拽的视图与当前进入的子控件视图是否是同一个，如果是则不删除添加操作
                    if (touchIndex > -1 && dragedView != null && dragedView != mGridLayout.getChildAt(touchIndex)) {
                        mGridLayout.removeView(dragedView);
                        mGridLayout.addView(dragedView, touchIndex);
                    }
                    break;
                default:
                    break;
            }
            return true;
        }
    };
    private DragedGridLayout mDragedGridLayout;
    private DragedGridLayout mHideGridLayout;

    private int getTouchIndex(DragEvent event) {
        //遍历矩形数组，看哪一个矩形包含了当前触摸点并返回即可
        for (int i = 0; i < mRects.length; i++) {
            Rect rect = mRects[i];
            if (rect.contains((int) event.getX(), (int) event.getY())) {
                return i;
            }
        }
        return -1;
    }

    private Rect[] mRects;

    //创建出与子控件对应的矩形对象
    private void initRects() {
        mRects = new Rect[mGridLayout.getChildCount()];
        for (int i = 0; i < mGridLayout.getChildCount(); i++) {
            View childView = mGridLayout.getChildAt(i);
            Rect rect = new Rect(childView.getLeft(), childView.getTop(), childView.getRight(), childView.getBottom());
            mRects[i] = rect;
        }
    }

    //SparseArray<String> 相当于hashmap,但更高效，谷歌官方推荐
    static SparseArray<String> dragEventType = new SparseArray<String>();

    static {
        dragEventType.put(DragEvent.ACTION_DRAG_STARTED, "STARTED");
        dragEventType.put(DragEvent.ACTION_DRAG_ENDED, "ENDED");
        dragEventType.put(DragEvent.ACTION_DRAG_ENTERED, "ENTERED");
        dragEventType.put(DragEvent.ACTION_DRAG_EXITED, "EXITED");
        dragEventType.put(DragEvent.ACTION_DRAG_LOCATION, "LOCATION");
        dragEventType.put(DragEvent.ACTION_DROP, "DROP");
    }

    public static String getDragEventAction(DragEvent de) {
        return dragEventType.get(de.getAction());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mGridLayout = (GridLayout) findViewById(R.id.gridLayout);
        mGridLayout.setOnDragListener(odl);

        mDragedGridLayout = (DragedGridLayout) findViewById(R.id.dragedGridLayout);
        List<String> items = new ArrayList<>();
        items.add("上海");
        items.add("昆山南");
        items.add("苏州");
        items.add("无锡");
        items.add("常州");
        items.add("丹阳");
        items.add("镇江");
        items.add("南京南");
        mDragedGridLayout.setAllowDrag(true);
        mDragedGridLayout.setItems(items);

        mHideGridLayout = (DragedGridLayout) findViewById(R.id.hideGridLayout);
        List<String> items1 = new ArrayList<>();
        items1.add("香港");
        items1.add("深圳");
        items1.add("北京");
        items1.add("广州");
        items1.add("杭州");
        items1.add("武汉");
        items1.add("厦门");
        items1.add("东莞");
        mHideGridLayout.setAllowDrag(false);
        mHideGridLayout.setItems(items1);

        //给控件设置接口监听
        mDragedGridLayout.setOnItemClickListener(new DragedGridLayout.OnItemClickListener() {
            @Override
            public void onItemClick(TextView view) {
                mDragedGridLayout.removeView(view);
                mHideGridLayout.addItem(view.getText().toString());
            }
        });
        mHideGridLayout.setOnItemClickListener(new DragedGridLayout.OnItemClickListener() {
            @Override
            public void onItemClick(TextView view) {
                mHideGridLayout.removeView(view);
                mDragedGridLayout.addItem(view.getText().toString());
            }
        });
    }

    public void addItem(View view) {
//        Dialog dialog = new Dialog(MainActivity.this, R.style.Style_dialog);
//        dialog.setContentView(R.layout.view_dialog);
//        Window window = dialog.getWindow();
//        WindowManager.LayoutParams attributes = window.getAttributes();
//        attributes.gravity = Gravity.TOP;
//        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        window.setAttributes(attributes);
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.show();

        DragDialog dragDialog = new DragDialog(MainActivity.this);
        List<String> items = new ArrayList<>();
        items.add("上海");
        items.add("昆山南");
        items.add("苏州");
        items.add("无锡");
        items.add("常州");
        items.add("丹阳");
        items.add("镇江");
        items.add("南京南");

        mHideGridLayout = (DragedGridLayout) findViewById(R.id.hideGridLayout);
        List<String> items1 = new ArrayList<>();
        items1.add("香港");
        items1.add("深圳");
        items1.add("北京");
        items1.add("广州");
        items1.add("杭州");
        items1.add("武汉");
        items1.add("厦门");
        items1.add("东莞");

        dragDialog.setShowAndHideItems(items, items1);
        dragDialog.show();
    }

    public void addItem1(View view) {
        //往mGridLayout添加条目
        TextView tv = new TextView(MainActivity.this);
        tv.setText(index + "");
        tv.setBackgroundResource(R.drawable.selector_tv_bg);
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
