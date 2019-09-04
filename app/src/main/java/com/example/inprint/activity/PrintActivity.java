package com.example.inprint.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.inprint.R;
import com.example.inprint.util.ActivityUtil;
import com.githang.statusbar.StatusBarCompat;

import java.text.DecimalFormat;

public class PrintActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout select_location;   //位置选择 控件
    private TextView tv_filename;             //文档名字 控件
    private TextView tv_page;                 //文档页数 控件
    private TextView tv_number;               //文档份数 控件
    private TextView tv_univalence;           //文档单价 控件

    private ImageView iv_add;                 //增加份数 控件
    private ImageView iv_sub;                 //减少份数 控件
    private ImageView zfb_img;                //支付图片 控件
    private ImageView wx_img;                 //微信图片 控件

    private Button view_doc;                  //预览文档 按钮
    private Button pay;                       //支付     按钮

    private String docUrl;                    //文档在服务器的地址
    private String docUri;                    //文档在手机中的定位
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        initView();
    }
    private void initView(){
        String filepage,filename;
        intent=getIntent();
        docUri=intent.getStringExtra("docUri");
        docUrl=intent.getStringExtra("docUrl");
        filepage=intent.getStringExtra("docPage");
        filename=intent.getStringExtra("fileName");

        StatusBarCompat.setStatusBarColor(this,
                getResources().getColor(R.color.white));
        select_location=findViewById(R.id.rl_address);
        tv_filename=findViewById(R.id.tv_filename);
        tv_page=findViewById(R.id.tv_total_pages);
        tv_univalence=findViewById(R.id.tv_totals_price);
        tv_number=findViewById(R.id.tv_number);
        iv_add=findViewById(R.id.print_number_add);
        iv_sub=findViewById(R.id.print_number_sub);
        zfb_img=findViewById(R.id.iv_zfb_gou);
        wx_img=findViewById(R.id.iv_weixin_weigou);

        view_doc=findViewById(R.id.print_view);
        pay=findViewById(R.id.print_cost);

        tv_filename.setText(filename);
        tv_page.setText(filepage);
        countMoney();

        select_location.setOnClickListener(this);
        iv_add.setOnClickListener(this);
        iv_sub.setOnClickListener(this);
        zfb_img.setOnClickListener(this);
        wx_img.setOnClickListener(this);
        view_doc.setOnClickListener(this);
        pay.setOnClickListener(this);
    }
    //计算价格
    private void countMoney(){
        DecimalFormat decimalFormat=new DecimalFormat("0.0");
        String page=tv_page.getText().toString();
        String number=tv_number.getText().toString();
        int numbers=Integer.parseInt(number);
        int pages=Integer.parseInt(page);
        float ftotal=pages*numbers*0.2f;
        String total=decimalFormat.format(ftotal);
        tv_univalence.setText(total);
    }
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.print_view:
                ActivityUtil.checkDoc(this,docUri);
                break;
        }
    }
}
