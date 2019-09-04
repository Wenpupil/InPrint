package com.example.inprint.util;

import android.content.Context;
import android.content.Intent;

import com.example.inprint.activity.DocViewActivity;

public class ActivityUtil {
    //查看文档
    public static void checkDoc(Context context,String clickDocName){
        int splitLine=clickDocName.lastIndexOf('/');
        String docUrl=clickDocName.substring(0,splitLine+1);
        String docName=clickDocName.substring(splitLine+1);
        LogUtil.d("DocFragment-文档URL分割",docUrl+"  , "+docName);
        Intent intent=new Intent(context, DocViewActivity.class);
        intent.putExtra("docName",docName);
        intent.putExtra("docUrl",docUrl);
        context.startActivity(intent);
    }
}
