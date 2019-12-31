package com.example.inprint.util;

import com.example.inprint.bean.PDoc;
import com.example.inprint.bean.Porder;
import com.example.inprint.bean.Uorder;
import com.google.gson.Gson;

import java.io.File;
import java.util.List;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/*
 * @Author Wenpupil
 * @Time 2019.09.02
 * @Description App与服务器的数据交互工具
 */
public class HttpUtil {
    //提交文档--生成相应订单信息
    public static void postDoc(PDoc pDoc, Callback callback){
        File docfile=new File(pDoc.getUri());
        String postUrl=ConfigUtil.getPostDocAddress();

        OkHttpClient client=new OkHttpClient();
        MultipartBody.Builder builder=new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

        builder.addFormDataPart("docfile", docfile.getName(),
                RequestBody.create(MediaType.parse("application/octet-stream"),docfile))
                .addFormDataPart("aid",pDoc.getAid())
                .addFormDataPart("atoken",pDoc.getAtoken())
                .addFormDataPart("rate",pDoc.getRate());
        MultipartBody body=builder.build();
        LogUtil.d("Atoken",pDoc.getAtoken());
        Request request=new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
    //提交订单至服务器
    public static void postPorder(Porder pOrder, Callback callback){
        OkHttpClient client=new OkHttpClient();
        //将订单数据转化为json格式
        Gson gson=new Gson();
        String data=gson.toJson(pOrder, Porder.class);

        RequestBody body=new FormBody.Builder()
                .add("order",data)
                .build();

        LogUtil.d("上传订单",data);
        Request request=new Request.Builder()
                .url(ConfigUtil.getPostOrderAddress())
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
    //查询打印未完成的订单状态
    public static void queryOrderStatus(String aid,Callback callback){
        OkHttpClient okHttpClient=new OkHttpClient();
        RequestBody body=new FormBody.Builder()
                .add("aid",aid)
                .build();
        LogUtil.d("查询订单状态",aid);
        Request request=new Request.Builder()
                .url(ConfigUtil.getQueryOrderStatusAddress())
                .post(body)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
