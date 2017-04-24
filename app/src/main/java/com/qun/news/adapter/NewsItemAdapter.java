package com.qun.news.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qun.news.R;
import com.qun.news.bean.NewsItemBean;

import java.util.List;

/**
 * Created by Qun on 2017/4/24.
 */

public class NewsItemAdapter extends MyBaseAdapter<NewsItemBean.DataBean.NewsBean> {

    public NewsItemAdapter(Context context, List<NewsItemBean.DataBean.NewsBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.layout_news_item, null);
            holder = new ViewHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.iv_img);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_date = (TextView) convertView.findViewById(R.id.tv_pub_date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        NewsItemBean.DataBean.NewsBean newsBean = getItem(position);
        holder.tv_title.setText(newsBean.getTitle());
        holder.tv_date.setText(newsBean.getPubdate());
        Glide.with(mContext.getApplicationContext()).load(newsBean.getListimage()).into(holder.iv);
        return convertView;
    }

    static class ViewHolder {
        ImageView iv;
        TextView tv_title;
        TextView tv_date;
    }
}
