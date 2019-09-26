package com.example.inprint.util;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
/*
 * @Author Wenpupil
 * @Description 状态栏操作工具
 */
public class StatusBarUtil {

    //使状态栏透明
    public static void setStatusBarFullTransparent(Activity activity) {
        if(Build.VERSION.SDK_INT >= 21){
            View decorView = activity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }
    //改变状态栏图标为深色
    public static void setStatusBarColor(Activity activity){
        activity.getWindow().getDecorView().
                setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
    //隐藏状态栏
    public static void hideStatusBar(Activity activity){
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    //设置Activity对应的顶部状态栏的透明
    public static void setWindowStatusBarColor(Activity activity, int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
