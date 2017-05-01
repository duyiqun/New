package com.qun.skywheeldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SeekBar mSeekBar;
    private SkyWheel mSkyWheel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mSkyWheel = (SkyWheel) findViewById(R.id.skyWheel);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double degree = Math.PI * 2 / 360 * progress;
                mSkyWheel.setDegree(degree);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        for (int i = 0; i < mSkyWheel.getChildCount(); i++) {
            View childView = mSkyWheel.getChildAt(i);
            childView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "v.getTag():" + v.getTag(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
