package com.example.inprint.myview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.inprint.R;

public class ConfirmDialog extends Dialog implements View.OnClickListener {

    private TextView contentTxt;
    private TextView submitTxt;
    private Context context;

    private OnCloseListener listener;
    private String content;

    public interface OnCloseListener{
        void onClick(Dialog dialog,boolean confirm);
    }
    public ConfirmDialog(Context context){
        super(context);
        this.context=context;
    }

    public ConfirmDialog(Context context,String content){
        super(context,R.style.Theme_AppCompat_Dialog);
        this.context=context;
        this.content=content;

    }

    public ConfirmDialog(Context context, String content, OnCloseListener listener){
        super(context,R.style.dialog);
        this.context=context;
        this.content=content;
        this.listener=listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_confirm);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView(){
        contentTxt=findViewById(R.id.content);
        submitTxt=findViewById(R.id.submit);
        submitTxt.setOnClickListener(this);

        contentTxt.setText(content);
    }

    @Override
    public void onClick(View v){
        if(v.getId()==R.id.submit)
        {
            if(listener!=null){
                listener.onClick(this,true);
            }
        }
    }
}
