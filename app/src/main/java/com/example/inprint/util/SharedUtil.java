package com.example.inprint.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedUtil {
    public static void deleteInt(Activity activity,String key){
        SharedPreferences sp=activity.getSharedPreferences("InTalk",
                Context.MODE_PRIVATE);
        sp.edit().remove(key).apply();
    }
    //写入int变量
    public static void writeInt(Activity activity, String key, int value){
        SharedPreferences sp=activity.getSharedPreferences("InTalk",
                Context.MODE_PRIVATE);
        sp.edit().putInt(key,value).apply();
    }
    //读取int变量
    public static int readInt(Activity activity,String key){
        SharedPreferences sp=activity.getSharedPreferences("InTalk",
                Context.MODE_PRIVATE);
        int result=sp.getInt(key,0);
        return result;
    }

}
