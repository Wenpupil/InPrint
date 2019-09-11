package com.example.inprint.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

import com.example.inprint.bean.Uorder;
import com.example.inprint.util.HttpUtil;
import com.example.inprint.util.LogUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author Wenpupil
 * @Description 用于更新订单状态信息
 */
public class QueryOrderService extends Service {
    private List<Uorder> uorders;
    private Gson gson;
    private Type type = new TypeToken<List<Uorder>>(){}.getType();
    public QueryOrderService(List<Uorder> uorders) {
        this.uorders = uorders;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        updateOrderStatus();
        //多少时间运行一次查询任务
        int longTime = 500;
        long triggerAtTime = SystemClock.elapsedRealtime()+longTime;
        Intent i = new Intent(this,QueryOrderService.class);
        PendingIntent pi = PendingIntent.getService(this,0,i,0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        return super.onStartCommand(intent,flags,startId);
    }
    //对服务器进行订单状态查询
    private void updateOrderStatus(){
        LogUtil.d("订单服务","查询订单状态任务启动");
        HttpUtil.queryOrderStatus(uorders, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                LogUtil.d("订单服务","出现错误");
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                if(gson == null){
                    gson = new Gson();
                }
                uorders = gson.fromJson(result,type);
                saveInDatabases();
            }
        });
    }
    //将即使订单状态保存至手机数据库中
    private void saveInDatabases(){
        for(int i=0;i<uorders.size();i++){
            Uorder uorder = uorders.get(i);
            Uorder position = LitePal.where("docUrl = ?",uorder.getDocUrl())
                    .findFirst(Uorder.class);
            position.setStatus(uorder.getStatus());
            position.save();

            if(uorder.getStatus().equals("1")){
                uorders.remove(i);              //打印任务完成，则从打印状态打印列表中删除
                i++;
                LogUtil.d("订单完成",uorder.viewInfo());
            }
        }
    }
}
