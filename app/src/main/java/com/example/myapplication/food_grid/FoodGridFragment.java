package com.example.myapplication.food_grid;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.myapplication.R;
import com.example.myapplication.bean.FoodBean;
import com.example.myapplication.bean.FoodUtils;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodGridFragment extends Fragment {


    View view;
    GridView gv;
    List<FoodBean> mDatas;
    private FoodGridAdapter adapter;

    public FoodGridFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_food_grid, container,false);
        gv = view.findViewById(R.id.food_grid_gv);
//        数据源
        mDatas = FoodUtils.getAllFoodList();
        //        创建适配器对象
        adapter = new FoodGridAdapter(getActivity(), mDatas);
//        设置适配器
        gv.setAdapter(adapter);
        setListener();
        return view;

    }

    private void setListener() {
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FoodBean foodBean = mDatas.get(position);
                Intent intent = new Intent(getActivity(), FoodDescActivity.class);
                intent.putExtra("food",foodBean);
                startActivity(intent);
            }
        });
    }
}
