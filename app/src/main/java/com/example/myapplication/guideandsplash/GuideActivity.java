package com.example.myapplication.guideandsplash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.loginandregister.LoginActivity;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {
//获取图片资源
    int[] imgRes = new int[]{
            R.drawable.guide_1,
            R.drawable.guide_2,
            R.drawable.guide_3,
            R.drawable.guide_4
};
    private List<View> mViewList = new ArrayList<>();
    private ViewPager mVpGuide;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initData();
        btnStart = (Button)findViewById(R.id.btn_start);
        mVpGuide = (ViewPager)findViewById(R.id.viewPager);
         mVpGuide.setAdapter(new MyPagerAdapter());
        mVpGuide.setOnPageChangeListener(new MyListener());
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    private void initData() {
        for(int i=0;i<imgRes.length;i++){
        View inflate = getLayoutInflater().inflate(R.layout.guide_item,null);
        ImageView ivGuide = inflate.findViewById(R.id.iv_guide);
        ivGuide.setBackgroundResource(imgRes[i]);
        mViewList.add(inflate);
        }
    }

    class MyPagerAdapter extends PagerAdapter{
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = mViewList.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mViewList.get(position));
        }

        @Override
        public int getCount() {
            return imgRes.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

    }
    class MyListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }
          //ViewPager滚动到第几张
        @Override
        public void onPageSelected(int position) {
             if(position == imgRes.length - 1){
                 btnStart.setVisibility(View.VISIBLE);
                 Animation animation = AnimationUtils.loadAnimation(GuideActivity.this, R.anim.anim);
                 btnStart.startAnimation(animation);
             }
             else{
                 btnStart.setVisibility(View.GONE);
             }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
