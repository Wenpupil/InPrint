package com.example.inprint.myview;

import android.app.Activity;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
    private Activity activity;
    private int width;
    public MyGestureListener(Activity activity){
        this.activity = activity;
        width = activity.getWindowManager().getDefaultDisplay().getWidth();
    }
    public void setActivity(Activity activity){
        this.activity = activity;
    }
    @Override
    public boolean onFling(MotionEvent e1,MotionEvent e2,
                           float velocityX, float velocityY){
        if(e2.getX()-e1.getX()>(width/3)){
            activity.finish();
        }
        return true;
    }
}
