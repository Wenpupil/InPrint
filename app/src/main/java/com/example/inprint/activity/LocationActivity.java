package com.example.inprint.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.inprint.R;
import com.example.inprint.adapter.LocationAdapter;
import com.example.inprint.bean.Location;
import com.example.inprint.myview.MyGestureListener;
import com.example.inprint.util.LogUtil;
import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity implements View.OnClickListener{

    private GestureDetectorCompat mDetector;
    private MyGestureListener myGestureListener;
    private RecyclerView recyclerView;                //列表 控件
    private List<Location> locationList;
    private LocationAdapter adapter;
    private ImageView back;                           //退出按钮

    //点击位置信息列表后所作出的相应动作
    private LocationAdapter.OnItemClickListener listener=new LocationAdapter.OnItemClickListener() {
        @Override
        public void onClick(View v, final Location location) {
            LogUtil.d("位置信息","您选择的是:"+location.getLocationId());
            Intent intent=new Intent();
            intent.putExtra("locationId",location.getLocationId());
            intent.putExtra("locationName",location.getLoc());
            setResult(RESULT_OK,intent);
            finish();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        iniView();
    }
    //初始化控件信息
    private void iniView(){
        StatusBarCompat.setStatusBarColor(this,
                getResources().getColor(R.color.white));

        iniLocationList();
        adapter=new LocationAdapter(locationList);
        adapter.setItemListener(listener);
        recyclerView = findViewById(R.id.rv_location_list);
        back = findViewById(R.id.iv_userHead);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        back.setOnClickListener(this);
        iniTouch();                    //初始化手势动作
    }
    private void iniTouch(){
        myGestureListener = new MyGestureListener(this);
        mDetector = new GestureDetectorCompat(this, myGestureListener);
    }
    //初始化位置信息列表----测试UI函数
    private void iniLocationList(){
        locationList=new ArrayList<>();
        Location a=new Location("001","上海海洋大学一小区",0);
        locationList.add(a);
        Location b=new Location("002","上海海洋大学二小区",1);
        locationList.add(b);
        Location c=new Location("003","上海海洋大学三小区",3);
        locationList.add(c);
    }
    @Override
    public void onClick(View view){
        finish();
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
    @Override
    public boolean onTouchEvent(MotionEvent e){
        this.mDetector.onTouchEvent(e);
        return super.onTouchEvent(e);
    }
}
