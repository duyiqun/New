package com.qun.news.act;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.VideoView;

import com.qun.news.R;

public class SplashActivity extends AppCompatActivity {

    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView() {
        mVideoView = (VideoView) findViewById(R.id.videoView);
        //播放视频
//        mVideoView.setVideoURI(Uri.parse("http://kr.mp4"));
        mVideoView.setVideoURI(Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.kr36));
        mVideoView.start();
    }

    public void enterHome(View view) {

    }
}
