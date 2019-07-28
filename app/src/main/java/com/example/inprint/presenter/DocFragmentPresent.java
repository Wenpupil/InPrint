package com.example.inprint.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.inprint.util.DialogUtil;

public class DocFragmentPresent {

    private Context context;
    /* 0表示点击文档
     * 1表示点击添加
     */
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message message){
            switch (message.what){
                case 0:
                    Toast.makeText(context,"你点击了文档 "+(String)message.obj
                    ,Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    DialogUtil.DocAdd(context);
                    break;
            }
        }
    };

    public DocFragmentPresent(Context context){
        this.context=context;
    }
    public void notifyMessage(int what,String obj){
        Message message=new Message();
        message.what=what;
        message.obj=obj;
        handler.sendMessage(message);
    }
}
