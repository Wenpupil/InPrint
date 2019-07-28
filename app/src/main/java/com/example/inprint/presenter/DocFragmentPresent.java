package com.example.inprint.presenter;

import android.app.Activity;
import android.content.Context;

/*
 * @Author Wenpuils
 * @Description 关于文档碎片对象中间件，负责UI，Action，Model处理
 */
public class DocFragmentPresent {

    private Context context;
    private Activity activity;
    public DocFragmentPresent(Context context){
        this.context=context;
    }

    public void setActivity(Activity activity){
        this.activity=activity;
    }
}
