package com.example.inprint.util;

import com.example.inprint.bean.PDoc;

import java.io.File;

import okhttp3.Callback;
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
        String postUrl=pDoc.getPosturl();

        OkHttpClient client=new OkHttpClient();
        MultipartBody.Builder builder=new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

        builder.addFormDataPart("docfile", docfile.getName(),
                RequestBody.create(MediaType.parse("application/octet-stream"),docfile))
                .addFormDataPart("aid",pDoc.getAid())
                .addFormDataPart("rate",pDoc.getRate());
        MultipartBody body=builder.build();

        Request request=new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
