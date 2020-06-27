package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.banner.DataBean;
import com.example.myapplication.banner.ImageAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnPageChangeListener;
import com.youth.banner.transformer.RotateYTransformer;
import com.youth.banner.util.BannerUtils;
import com.youth.banner.util.LogUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment {

    private View view;
    Banner banner;
    TextView shareTv;


    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_about, container,false);
        banner = view.findViewById(R.id.banner);
        shareTv = view.findViewById(R.id.about_tv_share);
        shareTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String msg= "健康饮食非常的重要，了解饮食各种营养素和热量，摄入正确的食物，让你变得更健康，想要了解更多么，快来下载健康饮食app吧~~";
                intent.putExtra(Intent.EXTRA_TEXT,msg);
                startActivity(Intent.createChooser(intent,"健康饮食分享"));
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageAdapter adapter = new ImageAdapter(DataBean.getTestData());
        banner.addBannerLifecycleObserver(this)//添加生命周期观察者
                .setAdapter(adapter)
                .setIndicator(new CircleIndicator(getActivity()))
                .setBannerRound(BannerUtils.dp2px(5))//圆角
                .addPageTransformer(new RotateYTransformer())//添加切换效果
                .start();
    }
}
