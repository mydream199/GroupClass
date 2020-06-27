package com.example.myapplication.food_list;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.bean.FoodBean;
import com.example.myapplication.bean.FoodUtils;
import com.example.myapplication.food_grid.FoodDescActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoListFragment extends Fragment {

    private View view;
    EditText searchEt;
    ImageView searchIv,flushIv;
    ListView showLv;
    List<FoodBean> mDatas;
    List<FoodBean>allFoodList;
    private InfoListAdapter adapter;


    public InfoListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_info_list, container,false);
        searchEt = view.findViewById(R.id.info_et_search);
        searchIv = view.findViewById(R.id.info_iv_search);
        flushIv = view.findViewById(R.id.info_iv_flush);
        showLv = view.findViewById(R.id.infolist_lv);

        searchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                1.获取输入内容，判断不为空
                String msg = searchEt.getText().toString().trim();  //获取输入信息
                if (TextUtils.isEmpty(msg)) {
                    Toast.makeText(getActivity(),"输入内容不能为空！",Toast.LENGTH_SHORT).show();
                    return;
                }
//                判断所有食物列表的标题是否包含输入内容，如果包含，就添加到小集合中
                List<FoodBean>list = new ArrayList<>();
                for (int i = 0; i < allFoodList.size(); i++) {
                    String title = allFoodList.get(i).getTitle();
                    if (title.contains(msg)) {
                        list.add(allFoodList.get(i));
                    }
                }
                flushIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        searchEt.setText("");
                        mDatas.clear();
                        mDatas.addAll(allFoodList);
                        adapter.notifyDataSetChanged();
                    }
                });
                mDatas.clear();   // 清空ListView的适配器数据源内容
                mDatas.addAll(list);  // 添加新的数据到数据源中
                adapter.notifyDataSetChanged(); // 提示适配器更新
            }
        });


        mDatas = new ArrayList<>();
        allFoodList = FoodUtils.getAllFoodList();
        mDatas.addAll(allFoodList);
        adapter = new InfoListAdapter(this.getActivity(), mDatas);//设置适配器
        showLv.setAdapter(adapter);
        //        设置单向点击监听功能
        setListener();
        return view;
    }

    private void setListener() {
        showLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            FoodBean foodBean = mDatas.get(position);
            Intent intent = new Intent(getActivity(), FoodDescActivity.class);
            intent.putExtra("food",foodBean);
            startActivity(intent);
        }
    });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


}
