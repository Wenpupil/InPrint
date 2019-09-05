package com.example.inprint.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.inprint.R;
import com.example.inprint.adapter.LocationAdapter;
import com.example.inprint.bean.Location;
import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;                //列表 控件
    private List<Location> locationList;
    private LocationAdapter adapter;
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
        recyclerView=findViewById(R.id.rv_location_list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }
    //初始化位置信息列表----测试UI函数
    private void iniLocationList(){
        locationList=new ArrayList<>();
        Location a=new Location("上海海洋大学一小区",0);
        locationList.add(a);
        Location b=new Location("上海海洋大学二小区",1);
        locationList.add(b);
        Location c=new Location("上海海洋大学三小区",3);
        locationList.add(c);
    }
}
