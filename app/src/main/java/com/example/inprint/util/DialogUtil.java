package com.example.inprint.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import com.example.inprint.myview.ConfirmDialog;

/**
 * 对话框工具
 * 返回相应的对话框并且显示
 */
public class DialogUtil {
    public static void MyConfirm(String msg, Context context){
        new ConfirmDialog(context,
                msg, new ConfirmDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if(confirm){
                    dialog.dismiss();
                }
            }
        }).show();
    }
}
