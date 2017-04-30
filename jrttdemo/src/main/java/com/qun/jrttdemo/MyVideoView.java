package com.qun.jrttdemo;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.io.IOException;

/**
 * 专门用来播放视频的类
 * Created by Qun on 2017/4/30.
 */

class MyVideoView implements View.OnClickListener {

    private final Context mContext;
    private final View mView;
    private Surface mSurface;//类似与画布一样效果，放的是视频
    private MediaPlayer mMediaPlayer;
    private String mUrl;
    private final Button mPlay;


    public MyVideoView(Context context) {
        this.mContext = context;
        mView = View.inflate(mContext, R.layout.video_view_item, null);
        ProgressBar pb = (ProgressBar) mView.findViewById(R.id.pb);
        mPlay = (Button) mView.findViewById(R.id.play);
        mPlay.setOnClickListener(this);
        TextureView videoView = (TextureView) mView.findViewById(R.id.video_view);
        videoView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture sf, int width, int height) {
                mSurface = new Surface(sf);
                mMediaPlayer = new MediaPlayer();
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
    }

    public View getRootView() {
        return mView;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public void startPlay() {
        //让mediaplayer开始播放视频
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                playVideo();
            }
        }, 1000);
    }

    private void playVideo() {
        try {
//            mMediaPlayer.setDataSource(mContext, Uri.parse(this.mUrl));
            mMediaPlayer.setDataSource(mContext, Uri.parse("android.resource://" + mContext.getPackageName() + "/" + R.raw.kr36));
            mMediaPlayer.setSurface(mSurface);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.prepareAsync();

            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                    mPlay.setText("播放");
                } else {
                    mMediaPlayer.start();
                    mPlay.setText("暂停");
                }
                break;
            default:
                break;
        }
    }

    public void release() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
