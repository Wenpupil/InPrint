package com.example.inprint.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inprint.R;
import com.example.inprint.util.LogUtil;
import com.example.inprint.util.StatusBarUtil;
import com.githang.statusbar.StatusBarCompat;

public class BalanceActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView back;     //返回 按钮
    private TextView record;    //记录 控件
    private Button recharge;    //充值 按钮
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        initView();
    }
    private void initView(){
        back = findViewById(R.id.iv_back);
        record = findViewById(R.id.tv_content_right);
        recharge = findViewById(R.id.b_recharge);

        back.setOnClickListener(this);
        record.setOnClickListener(this);
        recharge.setOnClickListener(this);
        StatusBarUtil.setStatusBarFullTransparent(this);
        StatusBarCompat.setStatusBarColor(this,Color.TRANSPARENT,true);
    }
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.iv_back:{
                LogUtil.d("余额界面","点击了返回按钮");
                finish();
                break;
            }
            case R.id.tv_content_right:{
                LogUtil.d("余额界面","点击了明细按钮");
                break;
            }
            case R.id.b_recharge:{
                goRechargeActivity();
                LogUtil.d("余额界面","点击了充值按钮");
                break;
            }
        }
    }
    private void goRechargeActivity(){
        startActivity(new Intent(this, RechargeActivity.class));
        overridePendingTransition(
                R.anim.doc_view_from_right,R.anim.out_left);
    }
    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.no_slide,R.anim.doc_view_out_right);
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
}
