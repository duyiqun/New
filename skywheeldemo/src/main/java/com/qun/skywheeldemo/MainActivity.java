package com.qun.skywheeldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private SeekBar mSeekBar;
    private SkyWheel mSkyWheel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mSkyWheel = (SkyWheel) findViewById(R.id.skyWheel);
    }
}
