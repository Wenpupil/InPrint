package com.example.inprint.util;

import com.example.inprint.R;

import org.litepal.LitePalApplication;

public class ConfigUtil {

    //提交订单的地址
    public static String getPostOrderAddress(){
        return LitePalApplication.getContext()
                .getResources().getString(R.string.Http_postOrder_address);
    }
    //提交文档的地址
    public static String getPostDocAddress(){
        return LitePalApplication.getContext()
                .getResources().getString(R.string.Http_postDoc_address);
    }
    //提交订单状态查询地址
    public static String getQueryOrderStatusAddress(){
        return LitePalApplication.getContext()
                .getResources().getString(R.string.Http_queryOrderStatus_address);
    }
}
