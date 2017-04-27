package com.qun.news360demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Qun on 2017/4/27.
 */

public class News360Adapter extends BaseAdapter {

    private final Context mContext;
    private final List<JsonBean.DataBean> mDatas;

    public News360Adapter(Context context, List<JsonBean.DataBean> datas) {
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
    public int getItemViewType(int position) {
        JsonBean.DataBean dataBean = (JsonBean.DataBean) getItem(position);
        int size = dataBean.getImgs().size();
        if (size == 3) {//类型一：3张图片
            return 0;
        } else if (size == 1) {//类型二：1张图片
            return 1;
        } else {//类型三：没有图片
            return 2;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        JsonBean.DataBean dataBean = (JsonBean.DataBean) getItem(position);
        ViewHolder holder = null;
        switch (itemViewType) {
            case 0://类型一：3张图片
                if (convertView == null) {
                    convertView = View.inflate(mContext, R.layout.list_text3, null);
                    holder = new ViewHolder();
                    holder.tv = (TextView) convertView.findViewById(R.id.text);
                    holder.image1 = (ImageView) convertView.findViewById(R.id.image1);
                    holder.image2 = (ImageView) convertView.findViewById(R.id.image2);
                    holder.image3 = (ImageView) convertView.findViewById(R.id.image3);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.tv.setText(dataBean.getTitle());
                Glide.with(mContext).load(dataBean.getImgs().get(0)).into(holder.image1);
                Glide.with(mContext).load(dataBean.getImgs().get(1)).into(holder.image2);
                Glide.with(mContext).load(dataBean.getImgs().get(2)).into(holder.image3);

                break;
            case 1://类型二：1张图片
                if (convertView == null) {
                    convertView = View.inflate(mContext, R.layout.list_text2, null);
                    holder = new ViewHolder();
                    holder.tv = (TextView) convertView.findViewById(R.id.text);
                    holder.image1 = (ImageView) convertView.findViewById(R.id.image1);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                holder.tv.setText(dataBean.getTitle());
                Glide.with(mContext).load(dataBean.getImgs().get(0)).into(holder.image1);

                break;
            case 2://类型三：没有图片
                if (convertView == null) {
                    convertView = View.inflate(mContext, R.layout.list_text1, null);
                    holder = new ViewHolder();
                    holder.tv = (TextView) convertView.findViewById(R.id.text);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                holder.tv.setText(dataBean.getTitle());

                break;
            default:
                break;
        }
        return convertView;
    }

    static class ViewHolder {
        TextView tv;
        ImageView image1;
        ImageView image2;
        ImageView image3;
    }
}
