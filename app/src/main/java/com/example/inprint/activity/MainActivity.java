package com.example.inprint.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.inprint.R;
import com.example.inprint.presenter.GlideImageLoader;
import com.example.inprint.presenter.MainPresenter;
import com.example.inprint.util.LogUtil;
import com.example.inprint.util.SharedUtil;
import com.example.inprint.util.StatusBarUtil;
import com.githang.statusbar.StatusBarCompat;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView userhead;   //用户头像
    private ImageView more;       //更多选项
    private LinearLayout tab1;    //底部tab1
    private LinearLayout tab2;    //底部tab2
    private LinearLayout tab3;    //底部tab3

    private static int past;

    private MainPresenter mainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mainPresenter=new MainPresenter(this);
    }
    //初始化界面
    private void initView(){
        //初始化状态栏颜色与actionBar相同
        StatusBarCompat.setStatusBarColor(this,
                getResources().getColor(R.color.app_top_color));
        Banner banner=findViewById(R.id.banner);
        MainPresenter.iniBanner(banner);
        userhead=findViewById(R.id.iv_userHead);
        more=findViewById(R.id.iv_more);
        tab1=findViewById(R.id.ll_tab1);
        tab2=findViewById(R.id.ll_tab2);
        tab3=findViewById(R.id.ll_tab3);

        setListener();                                                                              //对控件设置监听
    }

    /**
     * 控件监听函数
     */
    private void setListener(){
        userhead.setOnClickListener(this);
        more.setOnClickListener(this);
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.ll_tab1:
                past=mainPresenter.changeTab(past,0);
                break;
            case R.id.ll_tab2:
                past=mainPresenter.changeTab(past,1);
                break;
            case R.id.ll_tab3:
                past=mainPresenter.changeTab(past,2);
                break;
        }
    }
    @Override
    public void onStart(){
        super.onStart();
    }
    @Override
    public void onResume() {
        super.onResume();
        past= SharedUtil.readInt(this,"past");
        LogUtil.d("onResume","past="+past);
        mainPresenter.changeTab(0,past);
    }
    @Override
    public void onPause(){
        super.onPause();
        SharedUtil.writeInt(this,"past",past);
    }
    @Override
    public void onStop(){
        super.onStop();
        SharedUtil.writeInt(this,"past",past);
        LogUtil.d("onStop","past="+past);
    }
    @Override
    public void onDestroy(){
        LogUtil.d("onDestroy","past="+0);
        SharedUtil.deleteInt(this,"past");
        super.onDestroy();
    }
}
