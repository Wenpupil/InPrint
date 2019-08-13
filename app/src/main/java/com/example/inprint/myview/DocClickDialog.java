package com.example.inprint.myview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.inprint.R;

public class DocClickDialog extends Dialog implements View.OnClickListener {

    private TextView PrintDoc;
    private TextView CheckDoc;
    private ClickDialog listener;
    private Context context;

    public interface ClickDialog{
        void onClick(Dialog dialog,int rate);
    }

    public DocClickDialog(Context context,ClickDialog listener){
        super(context, R.style.MyDialog);
        this.context=context;
        this.listener=listener;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_doc_click);

        setCanceledOnTouchOutside(true);
        initView();
    }
    private void initView(){
        PrintDoc=findViewById(R.id.print_doc);
        CheckDoc=findViewById(R.id.check_doc);

        PrintDoc.setOnClickListener(this);
        CheckDoc.setOnClickListener(this);
    }
    /*
     * 0表示打印文档
     * 1表示查看文档
     */
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.print_doc:{
                listener.onClick(this,0);
                break;
            }
            case R.id.check_doc:{
                listener.onClick(this,1);
                break;
            }
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
