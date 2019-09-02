package com.example.inprint.myview;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.example.inprint.R;

public class LoadingDialog extends Dialog{

    private Context mContext;

    public LoadingDialog(Context context) {
        super(context, R.style.dialog);
        this.mContext = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        setCanceledOnTouchOutside(false);
    }
}