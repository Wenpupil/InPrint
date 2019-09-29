package com.example.inprint.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inprint.activity.OrderInfoActivity;
import com.example.inprint.R;
import com.example.inprint.adapter.OrderAdapter;
import com.example.inprint.bean.Uorder;
import com.example.inprint.presenter.OrderFragmentPresent;
import com.example.inprint.util.DataUtil;
import com.example.inprint.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author Wenpupil
 * @Time 2019.09.07
 * @Description 展示订单列表
 */
public class OrderFragment extends Fragment {

    private View view;
    private List<Uorder> uorderList =new ArrayList<>();
    private OrderAdapter adapter;
    private OrderFragmentPresent orderFragmentPresent;
    private RelativeLayout rl_no_order;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        RecyclerView recyclerView;
        //用测试数据 初始化列表
        //DataUtil.orderItem(uorderList);
        orderFragmentPresent = new OrderFragmentPresent(getContext());
        uorderList = orderFragmentPresent.readOrderList();             //读取数据库订单数据
        uorderList = DataUtil.reverseList(uorderList);                 //订单链数据反向

        view = inflater.inflate(R.layout.order_fragment,container,false);
        initView();
        recyclerView=view.findViewById(R.id.rv_order);

        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        adapter=new OrderAdapter(uorderList);
        adapter.setContext(getContext());
        setOnItemClickListener();
        recyclerView.setAdapter(adapter);
        return view;
    }
    private void initView(){
        rl_no_order = view.findViewById(R.id.rl_no_order);
        LogUtil.d("hhh",uorderList.size()+"");
        if(uorderList.size() == 0){
            rl_no_order.setVisibility(View.VISIBLE);
        }else{
            rl_no_order.setVisibility(View.GONE);
        }
    }
    private void setOnItemClickListener(){
        adapter.setListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, Uorder uorder) {
                Intent intent = new Intent(getContext(), OrderInfoActivity.class);
                intent.putExtra("uorder_data",uorder);
                startActivity(intent);
            }
        });
    }
}
