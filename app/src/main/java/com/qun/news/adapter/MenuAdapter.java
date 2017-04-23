package com.qun.news.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qun.news.R;

import java.util.List;

/**
 * Created by Qun on 2017/4/23.
 */

public class MenuAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<String> mDatas;
    private int mClickPosition;//记录点击的条目索引

    public MenuAdapter(Context context, List<String> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.layout_item_menu, null);
            holder = new ViewHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.iv_menu_item);
            holder.tv = (TextView) convertView.findViewById(R.id.tv_menu_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(mDatas.get(position));
        if (position == mClickPosition) {//如果点击的索引与正在创建的条目索引一致，那么当前条目就是选中状态
            holder.tv.setTextColor(Color.RED);
            holder.iv.setBackgroundResource(R.mipmap.menu_arr_select);
        } else {
            holder.tv.setTextColor(Color.WHITE);
            holder.iv.setBackgroundResource(R.mipmap.menu_arr_normal);
        }
        return convertView;
    }

    public void setClickPosition(int position) {
        this.mClickPosition = position;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        ImageView iv;
        TextView tv;
    }
}
