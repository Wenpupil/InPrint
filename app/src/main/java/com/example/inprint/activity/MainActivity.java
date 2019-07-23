package com.example.inprint.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.inprint.R;
import com.example.inprint.presenter.GlideImageLoader;
import com.example.inprint.presenter.MainPresenter;
import com.example.inprint.util.StatusBarUtil;
import com.githang.statusbar.StatusBarCompat;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        StatusBarCompat.setStatusBarColor(this,
                getResources().getColor(R.color.app_main_color));
        Banner banner=findViewById(R.id.banner);
        MainPresenter.iniBanner(banner);
    }
}
