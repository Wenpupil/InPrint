package com.example.inprint.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.inprint.R;
import com.example.inprint.fragment.DocFragment;
import com.example.inprint.fragment.OrderFragment;
import com.example.inprint.fragment.UserFragment;
import com.example.inprint.presenter.MainPresenter;
import com.example.inprint.service.QueryOrderService;
import com.example.inprint.util.LogUtil;
import com.example.inprint.util.StatusBarUtil;
import com.githang.statusbar.StatusBarCompat;
import com.makeramen.roundedimageview.RoundedImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private RelativeLayout rl_main_top;

    private TextView mainTitle;   //顶部文本
    private LinearLayout tab1;    //底部tab1
    private LinearLayout tab2;    //底部tab2
    private LinearLayout tab3;    //底部tab3
    private RoundedImageView userImg; //用户头像

    DocFragment docFragment;
    OrderFragment orderFragment;
    UserFragment userFragment;
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
        StatusBarUtil.setStatusBarFullTransparent(this);
        StatusBarCompat.setStatusBarColor(this,
                Color.TRANSPARENT);
        userImg = findViewById(R.id.activity_main_user_img);
        mainTitle = findViewById(R.id.tv_titles);
        rl_main_top = findViewById(R.id.rl_main_top);
        tab1 = findViewById(R.id.ll_tab1);
        tab2 = findViewById(R.id.ll_tab2);
        tab3 = findViewById(R.id.ll_tab3);
        setListener();//对控件设置监听

        //启动订单查询服务
        Intent intent=new Intent(this,QueryOrderService.class);
        startService(intent);
    }

    /**
     * 控件监听函数
     */
    private void setListener(){
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.ll_tab1:
                select(0);
                selectDocList();
                break;
            case R.id.ll_tab2:
                select(1);
                selectOrderList();
                break;
            case R.id.ll_tab3:
                select(2);
                selectUser();
                break;
        }
    }
    //底部tab选择
    private void select(int i){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        past=mainPresenter.changeTab(past,i);
        switch(i)
        {
            case 0:
                if(docFragment == null){
                    docFragment = new DocFragment();
                }
                transaction.replace(R.id.fl_main,docFragment);
                break;
            case 1:
                if(orderFragment == null){
                    orderFragment = new OrderFragment();
                }
                transaction.replace(R.id.fl_main,orderFragment);
                break;
            case 2:
                if(userFragment == null){
                    userFragment = new UserFragment();
                }
                transaction.replace(R.id.fl_main,userFragment);
                break;
        }
        transaction.commit();
        mainPresenter.setDocFragment(docFragment);
    }
    @Override
    public void onRestart(){
        super.onRestart();
    }
    @Override
    public void onStart(){
        super.onStart();
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onPause(){
        super.onPause();
    }
    @Override
    public void onStop(){
        super.onStop();
    }
    @Override
    public void onDestroy(){
        //订单状态查询服务取消
        Intent intent=new Intent(this,QueryOrderService.class);
        stopService(intent);

        LogUtil.d("onsDestroy","past="+past);
        super.onDestroy();
    }

    //文档获取的结果
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mainPresenter.docFromWhere(requestCode,data);
    }
    //选择文档列表的main视图变化
    private void selectDocList(){
        mainTitle.setText(getResources().getString(R.string.main_top_0));
        mainTitle.setTextColor(getResources().getColor(R.color.white));
        StatusBarCompat.setStatusBarColor(this,
                Color.TRANSPARENT);
        rl_main_top.setBackgroundColor(Color.TRANSPARENT);
        userImg.setVisibility(View.VISIBLE);
    }
    //选择订单列表的order视图变化
    private void selectOrderList(){
        mainTitle.setText(getResources().getString(R.string.main_top_1));
        /*mainTitle.setTextColor(getResources().getColor(R.color.my_black));
        StatusBarCompat.setStatusBarColor(this,
                getResources().getColor(R.color.white));
        rl_main_top.setBackgroundColor(getResources().getColor(R.color.white));*/

        mainTitle.setTextColor(getResources().getColor(R.color.white));
        StatusBarCompat.setStatusBarColor(this,
                Color.TRANSPARENT);
        rl_main_top.setBackgroundColor(Color.TRANSPARENT);
        userImg.setVisibility(View.VISIBLE);
    }
    //选择我的界面
    private void selectUser(){
        mainTitle.setText(getResources().getString(R.string.main_top_2));
        mainTitle.setTextColor(getResources().getColor(R.color.my_black));
        StatusBarCompat.setStatusBarColor(this,
                getResources().getColor(R.color.white));
        rl_main_top.setBackgroundColor(getResources().getColor(R.color.white));
        userImg.setVisibility(View.GONE);
    }
}
