package com.example.inprint.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.inprint.bean.Rorder;
import com.example.inprint.bean.Uorder;
import com.example.inprint.util.ConfigUtil;
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
    private List<Rorder> rorders;
    private Gson gson;
    private Type type = new TypeToken<List<Rorder>>(){}.getType();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startId){

        AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        updateOrderStatus();
        //多少时间运行一次查询任务
        int longTime = 3000;
        long triggerAtTime = SystemClock.elapsedRealtime()+longTime;
        Intent i = new Intent(this,QueryOrderService.class);
        PendingIntent pi = PendingIntent.getService(this,0,i,0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        return super.onStartCommand(intent,flags,startId);
    }
    //对服务器进行订单状态查询
    private void updateOrderStatus(){
        LogUtil.d("订单服务","执行查询订单状态任务");
        HttpUtil.queryOrderStatus(ConfigUtil.getAid(), new Callback() {
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
                if(!result.equals("null")){
                    rorders = gson.fromJson(result,type);
                    saveInDatabases();
                }
            }
        });
    }
    //将即使订单状态保存至手机数据库中
    private void saveInDatabases(){
        LogUtil.d("订单服务","服务器中获取的订单数据");
        for(int i=0;i<rorders.size();i++){
            Rorder rorder = rorders.get(i);
            Uorder position = LitePal.where("docUrl = ?",rorder.getAurl())
                    .findFirst(Uorder.class);
            if(position != null) {
                position.setStatus(rorder.getAstatus());
                position.save();
                //发送广播通知订单列表刷新
                if(rorder.getAstatus().equals("1")){
                    LocalBroadcastManager localBroadcastManager = LocalBroadcastManager
                            .getInstance(this);
                    Intent intent = new Intent("com.example.inprint.ORDER_UPDATE");
                    intent.putExtra("newOrder",position);
                    localBroadcastManager.sendBroadcast(intent);
                    LogUtil.d("orderUpdate","send broadcast success");
                }
            }else{
                LogUtil.d("订单服务","本地没有这个数据");
            }
            /*if(uorder.getStatus().equals("1")){
                uorders.remove(i);              //打印任务完成，则从打印状态打印列表中删除
                i++;
                LogUtil.d("订单完成",uorder.viewInfo());
            }*/
            LogUtil.d("订单服务",rorder.viewInfo());
        }
    }
}
