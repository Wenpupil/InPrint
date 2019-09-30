package com.example.inprint.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.inprint.R;
import com.example.inprint.adapter.TalkAdapter;
import com.example.inprint.bean.Talk;
import com.example.inprint.util.StatusBarUtil;
import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener{

    private List<Talk> talkList;
    private TalkAdapter adapter;
    private RecyclerView recyclerView;

    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        initView();
        initTestData();
        adapter = new TalkAdapter(talkList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.smoothScrollToPosition(talkList.size()-1);

    }
    private void initView(){
        StatusBarUtil.setStatusBarFullTransparent(this);
        StatusBarCompat.setStatusBarColor(this, Color.TRANSPARENT);
        recyclerView = findViewById(R.id.rl_talk_fragment);

        back = findViewById(R.id.iv_back);
        back.setOnClickListener(this);
    }
    private void initTestData(){
        talkList = new ArrayList<>();
        Talk talk = new Talk();
        talk.setContent("你好,有什么需要帮助?");
        talk.setWhere(0);
        talkList.add(talk);
    }
    @Override
    public void onClick(View view){
        finish();
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
