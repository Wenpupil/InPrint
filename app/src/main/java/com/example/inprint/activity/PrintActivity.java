package com.example.inprint.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.inprint.R;
import com.githang.statusbar.StatusBarCompat;

public class PrintActivity extends AppCompatActivity {

    private TextView tdocUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        initView();
        Intent intent=getIntent();
        String sdocUrl=intent.getStringExtra("docUri");
        tdocUri=findViewById(R.id.tv_docUri);
        tdocUri.setText(sdocUrl);
    }
    private void initView(){
        //初始化状态栏颜色与actionBar相同
        StatusBarCompat.setStatusBarColor(this,
                getResources().getColor(R.color.app_top_color));
    }
}
