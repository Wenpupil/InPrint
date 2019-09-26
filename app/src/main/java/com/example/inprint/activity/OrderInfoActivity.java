package com.example.inprint.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.inprint.R;
import com.example.inprint.bean.Uorder;
import com.example.inprint.presenter.OrderInfoPresenter;
import com.example.inprint.util.DialogUtil;
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

    private OrderInfoPresenter orderInfoPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        orderInfoPresenter = new OrderInfoPresenter();
        orderInfoPresenter.setContext(this);
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

        if(uorder != null) {
            String time = orderInfoPresenter.modifyTimeFormat(uorder.getTime());
            String page = orderInfoPresenter.calculatePage(uorder.getCost(),uorder.getNumber());    //计算文档页数
            DocPage.setText(page);
            DocName.setText(uorder.getDocName());                                                   //设置文档名
            DocSum.setText(uorder.getNumber());                                                     //设置文档数量
            OrderTime.setText(time);                                                                //订单时间
            OrderTotal.setText(uorder.getCost());                                                   //订单费用
            OrderLocation.setText(uorder.getWhere());                                               //打印地点
            if(uorder.getStatus().equals("1")){
                OrderNumber.setText(uorder.getWhere());                                             //文档柜号位置
            }

            TakeDoc.setOnClickListener(this);
        }else{
            LogUtil.d("订单信息","uorder对象为空");
        }

        StatusBarCompat.setStatusBarColor(this,
                getResources().getColor(R.color.white));
    }
    @Override
    public void onClick(View view){
        if(uorder.getStatus().equals("0")){
            DialogUtil.MyConfirm("打印未完成，无法取件",this);
        }else{
            orderInfoPresenter.openPrintDoor();
        }
    }

}
