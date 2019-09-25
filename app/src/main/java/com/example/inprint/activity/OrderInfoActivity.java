package com.example.inprint.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.inprint.R;
import com.example.inprint.bean.Uorder;
import com.example.inprint.util.LogUtil;
import com.githang.statusbar.StatusBarCompat;

public class OrderInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView DocName;
    private TextView DocPage;
    private TextView DocSum;
    private TextView OrderTime;
    private TextView OrderTotal;
    private TextView OrderLocation;
    private TextView OrderNumber;
    private Button TakeDoc;

    private Uorder uorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
    }
    private void initView(){
        Intent intent = getIntent();
        uorder = (Uorder)intent.getSerializableExtra("uorder_data");
        DocName = findViewById(R.id.tv_order_doc_name);
        DocPage = findViewById(R.id.tv_page_end);
        DocSum = findViewById(R.id.tv_order_sum_end);
        OrderTime = findViewById(R.id.tv_order_time_end);
        OrderTotal = findViewById(R.id.tv_order_total);
        OrderLocation = findViewById(R.id.tv_order_location_end);
        OrderNumber = findViewById(R.id.tv_order_number_end);
        TakeDoc = findViewById(R.id.tv_order_take_doc);

        DocName.setText(uorder.getDocName());
        DocPage.setText(uorder.getNumber());
        DocSum.setText(uorder.getNumber());
        OrderTime.setText(uorder.getTime());
        OrderTotal.setText(uorder.getCost());
        OrderLocation.setText(uorder.getWhere());
        OrderNumber.setText("0");

        StatusBarCompat.setStatusBarColor(this,
                getResources().getColor(R.color.white));
    }
    @Override
    public void onClick(View view){
        if(view.getId() == R.id.tv_order_take_doc){
            LogUtil.d("取件测试","点击了取件按钮");
        }
    }
}
