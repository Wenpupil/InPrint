package com.example.inprint.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
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
    private Context context;
    private RecyclerView recyclerView;

    private LocalBroadcastManager localBroadcastManager;
    private OrderUpdateReceive orderUpdateReceive;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

       context = getContext();
        //用测试数据 初始化列表
        //DataUtil.orderItem(uorderList);
        view = inflater.inflate(R.layout.order_fragment,container,false);
        recyclerView=view.findViewById(R.id.rv_order);
        task();
        initView();
        broadcastRegister();
        return view;
    }
    private void task()
    {
        orderFragmentPresent = new OrderFragmentPresent(context);
        uorderList = orderFragmentPresent.readOrderList();             //读取数据库订单数据
        uorderList = DataUtil.reverseList(uorderList);                 //订单链数据反向
        LinearLayoutManager manager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
        adapter=new OrderAdapter(uorderList);
        adapter.setContext(getContext());
        setOnItemClickListener();
        recyclerView.setAdapter(adapter);
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
    //广播初始化
    private void broadcastRegister(){
        localBroadcastManager = LocalBroadcastManager.getInstance(context);
        orderUpdateReceive = new OrderUpdateReceive();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.inprint.ORDER_UPDATE");
        localBroadcastManager.registerReceiver(orderUpdateReceive,intentFilter);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(orderUpdateReceive);
    }
    private void updateOrder(Uorder newOrder){
        String newOrderUrl = newOrder.getDocUrl();
        for(Uorder uorder: uorderList){
            if(uorder.getDocUrl().equals(newOrderUrl)){
                uorder.setStatus(newOrder.getStatus());
            }
        }
    }
    //当在order列表界面就刷新
    class OrderUpdateReceive extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent){
            Uorder uorder = (Uorder) intent.getSerializableExtra("newOrder");
            updateOrder(uorder);
            adapter.notifyDataSetChanged();
            LogUtil.d("orderUpdate","receive broadcast success");
        }
    }
}
