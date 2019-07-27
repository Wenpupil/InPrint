package com.example.inprint.myview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inprint.R;

public class DocAddDialog extends Dialog implements View.OnClickListener {

    private TextView title;
    private TextView qqText;
    private TextView phoneText;
    private TextView wxText;

    private ImageView qq;
    private ImageView phone;
    private ImageView wx;
    private Context context;
    private AddClickListener listener;

    public interface AddClickListener{
        void onClick(Dialog dialog,int rate);
    }

    public DocAddDialog(Context context, AddClickListener listener){
        super(context, R.style.MyDialog);
        this.context=context;
        this.listener=listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_doc_add);
        //设置触摸外面可取消对话框
        setCanceledOnTouchOutside(true);
        iniView();
    }
    private void iniView(){
        qq=findViewById(R.id.iv_doc_qq);
        phone=findViewById(R.id.iv_doc_phone);
        wx=findViewById(R.id.iv_doc_wx);
        title=findViewById(R.id.tv_add);
        qqText=findViewById(R.id.tv_qq);
        phoneText=findViewById(R.id.tv_phone);
        wxText=findViewById(R.id.tv_wx);

        qq.setOnClickListener(this);
        phone.setOnClickListener(this);
        wx.setOnClickListener(this);
    }
    /*
     * 0表示qq
     * 1表示手机
     * 2表示微信
     */
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.iv_doc_qq:
                listener.onClick(this,0);
                break;
            case R.id.iv_doc_phone:
                listener.onClick(this,1);
                break;
            case R.id.iv_doc_wx:
                listener.onClick(this,2);
                break;
        }
    }
    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity= Gravity.BOTTOM;
        layoutParams.width= WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height= WindowManager.LayoutParams.WRAP_CONTENT;

        getWindow().getDecorView().setPadding(0, 0, 0, 0);

        getWindow().setAttributes(layoutParams);

    }
}
