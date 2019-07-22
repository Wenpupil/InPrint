package com.example.inprint.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inprint.R;
import com.example.inprint.presenter.LoginPresenter;
import com.example.inprint.util.StatusBarUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView register;                                                                      //注册
    private TextView tv_sms;                                                                        //动态登入
    private TextView tv_lst;                                                                        //忘记密码
    private EditText et_name;                                                                       //用户名
    private EditText et_pass;                                                                       //密码
    private Button btn_login;                                                                       //登入
    private ImageView iv_back;                                                                      //关闭
    private ImageView iv_qq;                                                                        //qq登入
    private ImageView iv_phone;                                                                     //设备登入
    private ImageView iv_wx;                                                                        //微信登入

    private LoginPresenter lp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();                                                                                     //初始化控件
    }
    private void init(){
        register=findViewById(R.id.tv_register);
        tv_sms=findViewById(R.id.tv_phone_login);
        tv_lst=findViewById(R.id.tv_lost_pass);
        et_name=findViewById(R.id.et_username);
        et_pass=findViewById(R.id.et_password);
        btn_login=findViewById(R.id.btn_login);
        iv_back=findViewById(R.id.iv_back);
        iv_qq=findViewById(R.id.iv_qq);
        iv_phone = findViewById(R.id.iv_phone);
        iv_wx=findViewById(R.id.iv_wx);
        lp=new LoginPresenter(this);
        StatusBarUtil.setStatusBarFullTransparent(this);                                            //状态栏透明
        StatusBarUtil.setStatusBarColor(this);                                                      //状态栏文字黑色
        //设置控件为监听状态
        setListener();
    }

    /**
     * 监听各个控件
     */
    private void setListener(){
        register.setOnClickListener(this);
        tv_sms.setOnClickListener(this);
        tv_lst.setOnClickListener(this);
        et_name.setOnFocusChangeListener(lp.focusListener(et_name));
        et_pass.setOnFocusChangeListener(lp.focusListener(et_pass));
        btn_login.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        iv_qq.setOnClickListener(this);
        iv_phone.setOnClickListener(this);
        iv_wx.setOnClickListener(this);
    }

    /**
     * 各个控件相应的行为
     */
    @Override
    public void onClick(View v){                                                                    //各个控件行为
        switch(v.getId()){
            case R.id.tv_register:{
                break;
            }
            case R.id.tv_phone_login:{
                break;
            }
            case R.id.tv_lost_pass:{
                break;
            }
            case R.id.btn_login:{
                lp.login(et_name,et_pass);
                break;
            }
            case R.id.iv_back:{
                break;
            }
            case R.id.iv_qq:{
                break;
            }
            case R.id.iv_phone:{
                break;
            }
            case R.id.iv_wx:{
                break;
            }
        }
    }
}
