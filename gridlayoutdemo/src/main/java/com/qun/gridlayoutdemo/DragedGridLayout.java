package com.qun.gridlayoutdemo;

import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义出一个setItems方法，用来将频道标题数据传递给控件后，自动将子控件显示出来
 * 定义出一个setAllowDrag方法，用来控制控件是否可以进行拖拽操作
 * Created by Qun on 2017/5/1.
 */

public class DragedGridLayout extends GridLayout {

    public static final int COLUMN_COUNT = 4;
    private boolean flag;//用来标记是否可以进行拖拽操作
    private OnItemClickListener mOnItemClickListener;

    public DragedGridLayout(Context context) {
        this(context, null);
    }

    public DragedGridLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragedGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public DragedGridLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }

    private void init() {
//        android:columnCount="4"
//        android:animateLayoutChanges="true"
        this.setColumnCount(COLUMN_COUNT);
        this.setLayoutTransition(new LayoutTransition());
    }

    public void setItems(List<String> items) {
        for (String item : items) {
            addItem(item);
        }
    }

    public void addItem(String item) {
        TextView textView = newItemView();
        textView.setText(item);
        this.addView(textView);
    }

    private TextView newItemView() {
        //往mGridLayout添加条目
        TextView tv = new TextView(getContext());
        tv.setBackgroundResource(R.drawable.selector_tv_bg);
//        mGridLayout.addView(tv);
        int margin = 5;//5个像素
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels / 4 - 2 * margin;
        layoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
        tv.setLayoutParams(layoutParams);

        layoutParams.setMargins(margin, margin, margin, margin);
        tv.setGravity(Gravity.CENTER);

        if (this.flag) {
            tv.setOnLongClickListener(olcl);
        } else {
            tv.setOnLongClickListener(null);
        }

        tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick((TextView) v);
                }
            }
        });

        return tv;
    }

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
            dragedView.setEnabled(false);
//            v.startDragAndDrop(null, new View.DragShadowBuilder(v), null, 0);
            return false;
        }
    };

    public void setAllowDrag(boolean flag) {
        this.flag = flag;
        if (this.flag) {
            this.setOnDragListener(odl);
        } else {
            this.setOnDragListener(null);
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
                    if (touchIndex > -1 && dragedView != null && dragedView != DragedGridLayout.this.getChildAt(touchIndex)) {
                        DragedGridLayout.this.removeView(dragedView);
                        DragedGridLayout.this.addView(dragedView, touchIndex);
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    if (dragedView != null) {
                        dragedView.setEnabled(true);
                    }
                    break;
                default:
                    break;
            }
            return true;
        }
    };

    private Rect[] mRects;

    //创建出与子控件对应的矩形对象
    private void initRects() {
        mRects = new Rect[this.getChildCount()];
        for (int i = 0; i < this.getChildCount(); i++) {
            View childView = this.getChildAt(i);
            Rect rect = new Rect(childView.getLeft(), childView.getTop(), childView.getRight(), childView.getBottom());
            mRects[i] = rect;
        }
    }

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

    public List<String> getItems() {
        //遍历子控件返回数据即可
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < this.getChildCount(); i++) {
            TextView textview = (TextView) this.getChildAt(i);
            datas.add(textview.getText().toString());
        }
        return datas;
    }

    public interface OnItemClickListener {
        public void onItemClick(TextView view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
