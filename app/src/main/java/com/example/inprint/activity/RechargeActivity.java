package com.example.inprint.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.inprint.R;
import com.example.inprint.util.LogUtil;
import com.githang.statusbar.StatusBarCompat;

public class RechargeActivity extends AppCompatActivity implements View.OnClickListener{

    private int selectPattern =0;         //支付方式选择，0为支付宝，1为微信

    private EditText total;               //输入的金额
    private ConstraintLayout zfb;         //支付宝选项
    private ConstraintLayout wx;          //微信选项
    private Button recharge;              //确认按钮

    private ImageView back;               //返回按钮
    private ImageView iv_zfb;             //支付宝选项
    private ImageView iv_wx;              //微信选项
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        initView();
    }
    private void initView(){
        StatusBarCompat.setStatusBarColor(this, Color.WHITE);

        total = findViewById(R.id.et_recharge_total);
        zfb = findViewById(R.id.cl_recharge_zfb);
        wx = findViewById(R.id.cl_recharge_wx);
        recharge = findViewById(R.id.recharge);
        iv_zfb = findViewById(R.id.iv_zfb_select);
        iv_wx = findViewById(R.id.iv_wx_select);

        back = findViewById(R.id.iv_back);

        zfb.setOnClickListener(this);
        wx.setOnClickListener(this);
        recharge.setOnClickListener(this);
        back.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.cl_recharge_zfb:{
                selectZfb();
                break;
            }
            case R.id.cl_recharge_wx:{
                selectWx();
                break;
            }
            case R.id.recharge:{
                LogUtil.d("充值页面","点击了充值按钮");
                break;
            }
            case R.id.iv_back:{
                finish();
                break;
            }
        }
    }
    private void selectZfb(){
        iv_zfb.setImageResource(R.mipmap.gou);
        iv_wx.setImageResource(R.mipmap.weigou);
        selectPattern = 0;
    }
    private void selectWx(){
        iv_zfb.setImageResource(R.mipmap.weigou);
        iv_wx.setImageResource(R.mipmap.gou);
        selectPattern = 0;
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
}
