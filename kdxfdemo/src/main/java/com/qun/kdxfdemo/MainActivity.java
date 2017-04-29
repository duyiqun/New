package com.qun.kdxfdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.iflytek.speech.RecognizerResult;
import com.iflytek.speech.SpeechError;
import com.iflytek.ui.RecognizerDialog;
import com.iflytek.ui.RecognizerDialogListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private StringBuffer sb = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void recognize(View view) {
        RecognizerDialog recognizerDialog = new RecognizerDialog(MainActivity.this, "appid=5212ef0a");
        recognizerDialog.setListener(new RecognizerDialogListener() {
            //当有识别结果就进行返回
            @Override
            public void onResults(ArrayList<RecognizerResult> arrayList, boolean b) {
                for (RecognizerResult recognizerResult : arrayList) {
                    String text = recognizerResult.text;
                    sb.append(text);
                }
            }

            //当识别结束之后的调用
            @Override
            public void onEnd(SpeechError speechError) {
                if (speechError == null) {
                    System.out.println(sb.toString());
                }
                sb.setLength(0);
            }
        });
        recognizerDialog.show();


    }
}
