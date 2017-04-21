package com.qun.news.act;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qun.news.R;
import com.qun.news.utils.SpUtil;
import com.qun.news.view.MyVideoView;

public class SplashActivity extends AppCompatActivity {

    private MyVideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView() {
        mVideoView = (MyVideoView) findViewById(R.id.videoView);
        //播放视频
//        mVideoView.setVideoURI(Uri.parse("http://kr.mp4"));
        mVideoView.setVideoURI(Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.kr36));
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mVideoView.start();
            }
        });
        mVideoView.start();
    }

    public void enterHome(View view) {
//        判断是否是第一次进入应用，如果是则跳转引导界面，否则进入主界面
        boolean isFirst = SpUtil.getBoolean(SplashActivity.this, "isFirst", true);
        if (isFirst) {
            //进入引导界面
            SpUtil.saveBoolean(SplashActivity.this, "isFirst", false);
            startActivity(new Intent(SplashActivity.this, GuideActivity.class));
        } else {
            //进入主界面
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        }
        //停止视频
        if (mVideoView != null && mVideoView.isPlaying()) {
            mVideoView.stopPlayback();
        }
        finish();
    }
}
