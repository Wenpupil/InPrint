package com.example.inprint.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inprint.R;
import com.example.inprint.activity.LocationActivity;
import com.example.inprint.bean.POrder;
import com.example.inprint.util.DialogUtil;
import com.example.inprint.util.HttpUtil;
import com.example.inprint.util.LogUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.DecimalFormat;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PrintPresenter {
    private Context context;
    private static DecimalFormat decimalFormat;
    public PrintPresenter(Context context){
        this.context=context;
    }
    //计算文档单价
    public static String countUnivalence(TextView tv_page){
        decimalFormat=new DecimalFormat("0.0");
        String page=tv_page.getText().toString();
        int pages=Integer.parseInt(page);
        float ftotal=pages*0.2f;
        return decimalFormat.format(ftotal);
    }
    //计算文档*份数 合计的价格
    public static String countPrice(TextView tv_number,TextView tv_page){
        String numbers=tv_number.getText().toString();
        int number=Integer.parseInt(numbers);
        if(decimalFormat==null){
            decimalFormat=new DecimalFormat("0.0");
        }
        float ftotal=Float.parseFloat(countUnivalence(tv_page))*number;
        String total=decimalFormat.format(ftotal);
        return "￥" + total;
    }
    /* 文档份数改变，并更新UI
     * 0:表示减少
     * 1:表示增加
     */
    public void numberChange(TextView tv_number,int select){
        String snumber_now = tv_number.getText().toString();
        int inumber_now = Integer.parseInt(snumber_now);
        int inumber_new = newNumber(inumber_now,select);
        String snumber_new = inumber_new + "";
        tv_number.setText(snumber_new);
    }
    private int newNumber(int now,int select){
        if(select==1){
            now+=1;
        }else if(select==0&&now!=1){
            now-=1;
        }
        return now;
    }
    /* 更换支付方式UI
     * 0--选择支付宝支付
     * 1--选择微信支付
     */
    public void payPatternChange(ImageView zfb,ImageView wx,int select){
        if(select==0){
            zfb.setImageResource(R.mipmap.gou);
            wx.setImageResource(R.mipmap.weigou);
        }else{
            zfb.setImageResource(R.mipmap.weigou);
            wx.setImageResource(R.mipmap.gou);
        }
    }
    //点击支付按钮
    public void payCost(POrder order){
        HttpUtil.postPorder(order, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                LogUtil.d("上传订单","失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                LogUtil.d("上传订单","成功");
            }
        });
    }
    //提示打印地点未选择
    public void tipLocation(){
        DialogUtil.MyConfirm("未选择打印地点",context);
    }
    //启动选择位置信息
    public void selectLocation(Activity activity){
        Intent intent=new Intent(activity, LocationActivity.class);
        activity.startActivityForResult(intent,1);
    }
}
