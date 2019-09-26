package com.example.inprint.presenter;

import android.content.Context;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class OrderInfoPresenter {
    private Context context;
    public void setContext(Context context){
        this.context = context;
    }
    public String calculatePage(String cost,String number){
        float costs = Float.parseFloat(cost);
        int numbers = Integer.parseInt(number);
        double result = costs/(numbers * 0.2);
        int pages = (int) Math.ceil(result);
        return Integer.toString(pages);
    }
    public String modifyTimeFormat(String time){
        int index=0;
        for(int i=0;i<time.length();i++){
            if(time.charAt(i) == ' '){
                index = i;
                break;
            }
        }
        return time.substring(index+1);
    }
    public void openPrintDoor(){

    }
}
