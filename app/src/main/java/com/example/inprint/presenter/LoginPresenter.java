package com.example.inprint.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import com.example.inprint.util.DialogUtil;

/**
 * LoginActivity的对象中间件
 */
public class LoginPresenter {
    private Context context=null;
    public LoginPresenter(Context context){
        this.context=context;
    }
    //关于点击改变EditText的hint 返回相应的接口给活动中的监听调用
    public View.OnFocusChangeListener focusListener(final EditText et){
        final String temp=et.getHint().toString();
        View.OnFocusChangeListener et_listener=new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    et.setHint("");
                }else{
                    et.setHint(temp);
                }
            }
        };
        return et_listener;
    }
    public void login(EditText et_name, EditText et_pass){
        String username=et_name.getText().toString();
        String password=et_name.getText().toString();
        if(TextUtils.isEmpty(username)){
            DialogUtil.MyConfirm("账号不能为空",context);
            return;
        }
        if(TextUtils.isEmpty(password)){
            DialogUtil.MyConfirm("密码不能为空",context);
        }
    }
}
