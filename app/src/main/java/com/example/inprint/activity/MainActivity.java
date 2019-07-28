package com.example.inprint.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.inprint.R;
import com.example.inprint.fragment.DocFragment;
import com.example.inprint.presenter.MainPresenter;
import com.example.inprint.util.LogUtil;
import com.example.inprint.util.SharedUtil;
import com.githang.statusbar.StatusBarCompat;
import com.youth.banner.Banner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView userhead;   //用户头像
    private ImageView more;       //更多选项
    private LinearLayout tab1;    //底部tab1
    private LinearLayout tab2;    //底部tab2
    private LinearLayout tab3;    //底部tab3

    DocFragment docFragment;
    private static int past;

    private MainPresenter mainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mainPresenter=new MainPresenter(this);
        select(0);
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
        setListener();//对控件设置监听
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
                select(0);
                break;
            case R.id.ll_tab2:
                select(1);
                break;
            case R.id.ll_tab3:
                select(2);
                break;
        }
    }
    //底部tab选择
    private void select(int i){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        past=mainPresenter.changeTab(past,i);
        if(i==0){
            if(docFragment==null){
                docFragment=new DocFragment();
            }
            transaction.replace(R.id.fl_main,docFragment);
        }
        transaction.commit();
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
