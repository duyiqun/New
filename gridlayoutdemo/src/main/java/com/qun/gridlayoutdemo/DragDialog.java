package com.qun.gridlayoutdemo;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Qun on 2017/5/1.
 */

public class DragDialog extends Dialog {

    private final DragedGridLayout mShowDragedGridLayout;
    private final DragedGridLayout mHideDragedGridLayout;

    public DragDialog(@NonNull Context context) {
        super(context, R.style.Style_dialog);

//        Dialog dialog = new Dialog(MainActivity.this, R.style.Style_dialog);
        this.setContentView(R.layout.view_dialog);
        Window window = this.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = Gravity.TOP;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(attributes);
        this.setCanceledOnTouchOutside(true);

        //获取显示与隐藏的容器
        mShowDragedGridLayout = (DragedGridLayout) findViewById(R.id.showDragedGridLayout);
        mHideDragedGridLayout = (DragedGridLayout) findViewById(R.id.hideDragedGridLayout);
        mShowDragedGridLayout.setAllowDrag(true);
        mHideDragedGridLayout.setAllowDrag(false);

        mShowDragedGridLayout.setOnItemClickListener(new DragedGridLayout.OnItemClickListener() {
            @Override
            public void onItemClick(TextView view) {
                mShowDragedGridLayout.removeView(view);
                mHideDragedGridLayout.addItem(view.getText().toString());
            }
        });
        mHideDragedGridLayout.setOnItemClickListener(new DragedGridLayout.OnItemClickListener() {
            @Override
            public void onItemClick(TextView view) {
                mHideDragedGridLayout.removeView(view);
                mShowDragedGridLayout.addItem(view.getText().toString());
            }
        });
    }

    public void setShowAndHideItems(List<String> showItems, List<String> hideItems) {
        if (showItems != null && showItems.size() > 0)
            mShowDragedGridLayout.setItems(showItems);
        if (hideItems != null && hideItems.size() > 0)
            mHideDragedGridLayout.setItems(hideItems);
    }

    public List<String> getShowItems() {
        return mShowDragedGridLayout.getItems();
    }
}
