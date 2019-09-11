package com.example.inprint.presenter;

import android.content.Context;

import com.example.inprint.bean.Uorder;
import com.example.inprint.util.DialogUtil;
import com.example.inprint.util.LogUtil;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class OrderFragmentPresent {

    private Context context;
    public OrderFragmentPresent(Context context){
        this.context=context;
    }

    //点击订单子项取件--按钮 未完成打印时
    public void unableOpen(){
        LogUtil.d("取件","打印工作未完成");
        DialogUtil.MyConfirm("未完成打印",context);
    }
    //点击订单子项取件--按钮 完成打印时
    public void ableOpen(){
        LogUtil.d("取件","打印工作完成");
    }

    //读取数据库中所有的订单数据
    public List<Uorder> readOrderList(){
        return LitePal.findAll(Uorder.class);
    }

    /**
     * @param uorders List Uorder
     * 用于查找需要查询的订单链
     */
    public List<Uorder> queryOrderUnable(List<Uorder> uorders){
        List<Uorder> uorderList=new ArrayList<>();
        for(int i=0;i<uorders.size();i++){
            Uorder uorder=uorders.get(i);
            if(uorder.getStatus()!=null&&uorder.getStatus().equals("0")){
                uorderList.add(uorder);
            }
        }
        return uorderList;
    }
}
