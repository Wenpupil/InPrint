package com.example.inprint.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inprint.R;
import com.example.inprint.adapter.OrderAdapter;
import com.example.inprint.bean.Order;
import com.example.inprint.presenter.OrderFragmentPresent;
import com.example.inprint.util.TestDataUtil;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author Wenpupil
 * @Time 2019.09.07
 * @Description 展示订单列表
 */
public class OrderFragment extends Fragment {

    private List<Order> orderList=new ArrayList<>();
    private OrderAdapter adapter;
    private OrderFragmentPresent orderFragmentPresent;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view;
        RecyclerView recyclerView;
        //用测试数据 初始化列表
        TestDataUtil.orderItem(orderList);

        orderFragmentPresent=new OrderFragmentPresent(getContext());
        
        view = inflater.inflate(R.layout.order_fragment,container,false);
        recyclerView=view.findViewById(R.id.rv_order);
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        adapter=new OrderAdapter(orderList);
        adapter.setContext(getContext());
        setOnItemClickListener();
        recyclerView.setAdapter(adapter);
        return view;
    }
    private void setOnItemClickListener(){
        adapter.setListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, Order order) {
                if(order.getStatus().equals("0")){
                    orderFragmentPresent.unableOpen();
                }else{
                    orderFragmentPresent.ableOpen();
                }
            }
        });
    }
}
