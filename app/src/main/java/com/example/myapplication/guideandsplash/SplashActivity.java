package com.example.myapplication.guideandsplash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.loginandregister.LoginActivity;
import com.example.myapplication.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    Handler mHandler = new Handler();
    private int reclen = 3;
    private TextView tv;
    Timer timer = new Timer();
    private  Runnable runnable;



    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    reclen--;
                    tv.setText(reclen+"s");
                    if(reclen<0){
                        timer.cancel();
                        tv.setVisibility(View.GONE);
                    }
                }
            });
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag,flag);
        initView();

        timer.schedule(task,1000,1000);


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
                    intent.setClass(SplashActivity.this, LoginActivity.class);
                }

                startActivity(intent);
                //可以设置界面之间的切换动画
                overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
                finish();
            }
        },3000);
    }

    private void initView(){
        tv = findViewById(R.id.splashtv);
    }

}
