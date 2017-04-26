package com.qun.news.menu;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qun.news.Home.BasePage;
import com.qun.news.R;
import com.qun.news.adapter.NewsItemAdapter;
import com.qun.news.bean.NewsItemBean;
import com.qun.news.pulltorefresh.PullToRefreshBase;
import com.qun.news.pulltorefresh.PullToRefreshListView;
import com.qun.news.utils.DensityUtil;
import com.qun.news.utils.GsonTools;
import com.qun.news.utils.HMAPI;
import com.qun.news.utils.SpUtil;
import com.qun.news.view.RollViewPager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Qun on 2017/4/24.
 */

public class NewsItemPage extends BasePage {

    private final String mUrl;//对应频道的接口地址
    private PullToRefreshListView mLv;
    private NewsItemBean mNewsItemBean;

    private List<NewsItemBean.DataBean.NewsBean> news = new ArrayList<>();//用来记录新闻列表数据集合
    private boolean isReFresh;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (isReFresh) {//只有下拉刷新时，才进行热门新闻的操作
                //动态创建小圆点
                initDots(mNewsItemBean.getData().getTopnews().size());

                //在下拉刷新时，先将前一个消息循环清除再start
                if (mRollViewPager != null) {
                    mRollViewPager.stop();
                }

                //创建出专门用于显示轮播图效果的控件
                mRollViewPager = new RollViewPager(mContext);
                mRollViewPager.setTitles(mTopNewsTitle, mNewPagerTitles);
                mRollViewPager.setImages(mNewsPagerImages);
                mRollViewPager.setDots(dots);
                mRollViewPager.setOnItemClickListener(new RollViewPager.onItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(mContext, ("热门新闻被点击了" + position), Toast.LENGTH_SHORT).show();
                    }
                });
                mRollViewPager.start();

                //先删除之前的轮播图，再添加一个新的
                mTopNewsViewpager.removeAllViews();
                mTopNewsViewpager.addView(mRollViewPager);
            }


            //如果头视图已经添加了就不再重复添加即可
            int headerViewsCount = mLv.getRefreshableView().getHeaderViewsCount();
            if (headerViewsCount == 0) {
                mLv.getRefreshableView().addHeaderView(mTopView);
            }
//            mLv.getRefreshableView().addHeaderView(mTopView);
            if (mNewsItemAdapter == null) {
                mNewsItemAdapter = new NewsItemAdapter(mContext, news);
                mLv.getRefreshableView().setAdapter(mNewsItemAdapter);
            } else {
                mNewsItemAdapter.notifyDataSetChanged();
            }
            //刷新完毕后要通知控件刷新完成
            mLv.onPullUpRefreshComplete();//通过加载更多结束了
            mLv.onPullDownRefreshComplete();//通知下拉刷新结束了
            if (isReFresh) {
                String currentTime = getCurrentTime();
                SpUtil.saveString(mContext, "LastUpdatedLabel", currentTime);
                mLv.setLastUpdatedLabel(currentTime);
            }
        }
    };

    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    private LinearLayout mDotsLl;//专门显示小圆点的容器
    private List<ImageView> dots = new ArrayList<>();
    private TextView mTopNewsTitle;//用来显示轮播图的标题
    private LinearLayout mTopNewsViewpager;//用来显示轮播图的容器
    private NewsItemAdapter mNewsItemAdapter;
    private String mMoreUrl;//由于json数据中只有一个字段来获取新数据，下拉刷新与加载更多都使用这个地址
    private RollViewPager mRollViewPager;

    private void initDots(int size) {
        mDotsLl.removeAllViews();
        dots.clear();
        //将小圆点创建并添加到轮播图中显示
        for (int i = 0; i < size; i++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5));
            ImageView point = new ImageView(mContext);

            if (i == 0) {
                point.setImageResource(R.mipmap.dot_focus);
            } else {
                layoutParams.leftMargin = DensityUtil.dip2px(mContext, 5);
                point.setImageResource(R.mipmap.dot_normal);
            }
            point.setLayoutParams(layoutParams);
            mDotsLl.addView(point);
            dots.add(point);
        }
    }

    private View mTopView;//用于显示轮播图头视图

    public NewsItemPage(Context context, String url) {
        super(context);
        this.mUrl = url;
    }

    @Override
    public View initView() {
//        mLv = new ListView(mContext);
        View view = View.inflate(mContext, R.layout.frag_item_news, null);
        mLv = (PullToRefreshListView) view.findViewById(R.id.lv_item_news);
        //创建头视图view
        mTopView = View.inflate(mContext, R.layout.layout_roll_view, null);

        mDotsLl = (LinearLayout) mTopView.findViewById(R.id.dots_ll);
        mTopNewsTitle = (TextView) mTopView.findViewById(R.id.top_news_title);
        mTopNewsViewpager = (LinearLayout) mTopView.findViewById(R.id.top_news_viewpager);

        //设置下拉刷新与加载更多可用
        mLv.setPullRefreshEnabled(true);
        mLv.setPullLoadEnabled(true);
        mLv.setScrollLoadEnabled(false);

        mLv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            //下拉刷新操作
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (TextUtils.isEmpty(mMoreUrl)) {
                    Toast.makeText(mContext, "没有下拉刷新的新数据了", Toast.LENGTH_SHORT).show();
                    mLv.onPullDownRefreshComplete();
                } else {
                    getNetData(true, mMoreUrl);
                }
            }

            //加载更多操作
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (TextUtils.isEmpty(mMoreUrl)) {
                    Toast.makeText(mContext, "没有加载更多的新数据了", Toast.LENGTH_SHORT).show();
                    mLv.onPullUpRefreshComplete();
                } else {
                    getNetData(false, mMoreUrl);
                }
            }
        });
        return mLv;
    }

    @Override
    public void initData() {
        String json = SpUtil.getString(mContext, HMAPI.BASE_URL + this.mUrl, "");
        if (!TextUtils.isEmpty(json)) {
            parseJson(json, true);
        }
        getNetData(true, this.mUrl);

        //如果本地有更新时间就展示一下即可
        String lastUpdatedLabel = SpUtil.getString(mContext, "LastUpdatedLabel", "");
        mLv.setLastUpdatedLabel(lastUpdatedLabel);
    }

    /**
     * @param isRefresh 用于判断当前是否是下拉刷新
     * @param moreUrl
     */
    private void getNetData(final boolean isRefresh, String moreUrl) {
        this.isReFresh = isRefresh;
        Request request = new Request.Builder().url(HMAPI.BASE_URL + moreUrl).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                System.out.println(json);
                parseJson(json, isRefresh);
            }
        });
    }

    List<String> mNewPagerTitles = new ArrayList<>();
    List<String> mNewsPagerImages = new ArrayList<>();

    /**
     * @param json
     * @param isRefresh 用于判断当前是否是下拉刷新
     */
    private void parseJson(String json, boolean isRefresh) {

        SpUtil.saveString(mContext, HMAPI.BASE_URL + this.mUrl, json);

//        isLoad = true;
        mNewsItemBean = GsonTools.changeGsonToBean(json, NewsItemBean.class);

        //如果是下拉刷新要对热门新闻数据进行操作，要对新闻数据列表进行更新
        //如果是加载更多，之前的数据不变，更多的数据显示在后面即可

        if (isRefresh) {//下拉刷新
            //热门新闻的操作
            //封装标题
            //封装图片
            mNewPagerTitles.clear();
            mNewsPagerImages.clear();
            for (NewsItemBean.DataBean.TopnewsBean topnewsBean : mNewsItemBean.getData().getTopnews()) {
                mNewPagerTitles.add(topnewsBean.getTitle());
                mNewsPagerImages.add(topnewsBean.getTopimage());
            }
            //新闻列表操作(将数据全部刷新为最新的)
            news.clear();
            news.addAll(mNewsItemBean.getData().getNews());
        } else {//加载更多
            //新闻列表操作(将数据显示在现有的新闻之后)
            news.addAll(mNewsItemBean.getData().getNews());
        }

        //获取更多的数据接口地址
        mMoreUrl = mNewsItemBean.getData().getMore();

        mHandler.sendEmptyMessage(0);
    }
}
