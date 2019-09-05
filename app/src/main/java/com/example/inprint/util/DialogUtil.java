package com.example.inprint.util;

import android.app.Dialog;
import android.content.Context;
import com.example.inprint.myview.ConfirmDialog;
import com.example.inprint.myview.DocAddDialog;
import com.example.inprint.myview.DocClickDialog;

/**
 * 对话框工具
 * 返回相应的对话框并且显示
 */
public class DialogUtil {
    //弹出提示对话框
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
    public static void DocAdd(Context context){
        new DocAddDialog(context, new DocAddDialog.AddClickListener() {
            @Override
            public void onClick(Dialog dialog, int rate) {
                dialog.dismiss();
                LogUtil.d("AddDocTest","rate="+rate);
            }
        }).show();
    }
    public static void DocAdd(Context context, DocAddDialog.AddClickListener listener){
        new DocAddDialog(context,listener).show();
    }
    public static void DocClick(Context context, DocClickDialog.ClickDialog listener){
        new DocClickDialog(context,listener).show();
    }
}
