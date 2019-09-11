package com.example.inprint.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

import com.example.inprint.bean.Uorder;
import com.example.inprint.util.HttpUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
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
        HttpUtil.queryOrderStatus(uorders, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            }
        });
    }
}
