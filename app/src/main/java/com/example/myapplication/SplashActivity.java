package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp = getPreferences(MODE_PRIVATE);
                boolean isFirst = sp.getBoolean("isFirst",true);
                Intent intent = new Intent();
                if(isFirst){
                    //用户第一次进入
                    sp.edit().putBoolean("isFirst",false).commit();
                    intent.setClass(SplashActivity.this,GuideActivity.class);

                }else{
                    intent.setClass(SplashActivity.this,LoginActivity.class);
                }

                startActivity(intent);
                //可以设置界面之间的切换动画
                overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
                finish();
            }
        },3000);
    }
}
