package com.example.inprint.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GestureDetectorCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inprint.R;
import com.example.inprint.myview.MyGestureListener;
import com.example.inprint.util.LogUtil;
import com.example.inprint.util.StatusBarUtil;
import com.githang.statusbar.StatusBarCompat;

import org.w3c.dom.Text;

public class SetActivity extends AppCompatActivity implements View.OnClickListener{
    private GestureDetectorCompat mDetector;
    private MyGestureListener myGestureListener;

    private ImageView back;
    private ConstraintLayout userHead;          //头像
    private ConstraintLayout userName;          //名字
    private ConstraintLayout userPhone;         //手机
    private ConstraintLayout checkUpdate;       //检查更新
    private ConstraintLayout notify;            //消息通知

    private TextView username;                  //用户名
    private TextView userid;                    //识别号
    private TextView userphone;                 //手机号

    private TextView exitLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        initView();
    }
    private void initView(){
        StatusBarUtil.hideStatusBar(this);
        back = findViewById(R.id.iv_back);
        userHead = findViewById(R.id.cl_set_head);
        userName = findViewById(R.id.cl_set_name);
        userPhone = findViewById(R.id.cl_set_phone);
        checkUpdate = findViewById(R.id.cl_set_update);
        notify = findViewById(R.id.cl_set_notify);

        username = findViewById(R.id.tv_name);
        userid = findViewById(R.id.tv_id);
        userphone = findViewById(R.id.tv_phone);

        exitLogin = findViewById(R.id.tv_exit);

        back.setOnClickListener(this);
        userHead.setOnClickListener(this);
        userName.setOnClickListener(this);
        userPhone.setOnClickListener(this);
        checkUpdate.setOnClickListener(this);
        notify.setOnClickListener(this);
        exitLogin.setOnClickListener(this);
        iniTouch();     //初始化手势
    }
    private void iniTouch(){
        myGestureListener = new MyGestureListener(this);
        mDetector = new GestureDetectorCompat(this, myGestureListener);
    }
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.cl_set_head:{
                LogUtil.d("设置页面","点击头像");
                break;
            }
            case R.id.cl_set_name:{
                LogUtil.d("设置页面","点击名字");
                break;
            }
            case R.id.cl_set_phone:{
                LogUtil.d("设置页面","点击手机");
                break;
            }
            case R.id.cl_set_update:{
                LogUtil.d("设置页面","检查更新");
                break;
            }
            case R.id.cl_set_notify:{
                LogUtil.d("设置页面","消息通知");
                break;
            }
            case R.id.tv_exit:{
                LogUtil.d("设置页面","退出登入");
                break;
            }
            case R.id.iv_back:{
                finish();
                break;
            }
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent e){
        this.mDetector.onTouchEvent(e);
        return super.onTouchEvent(e);
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.no_slide,R.anim.doc_view_out_right);
    }
}
