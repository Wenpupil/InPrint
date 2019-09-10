package com.example.inprint.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.inprint.R;
import com.example.inprint.bean.POrder;
import com.example.inprint.presenter.PrintPresenter;
import com.example.inprint.util.ActivityUtil;
import com.example.inprint.util.LogUtil;
import com.githang.statusbar.StatusBarCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout select_location;   //位置选择 控件
    private TextView tv_address;              //位置名字 控件
    private TextView tv_filename;             //文档名字 控件
    private TextView tv_page;                 //文档页数 控件
    private TextView tv_number;               //文档份数 控件
    private TextView tv_univalence;           //文档单价 控件
    private TextView tv_price;                //支付价格 控件

    private ConstraintLayout zfb_item;        //支付宝横条
    private ConstraintLayout wx_item;         //微信横条

    private ImageView iv_add;                 //增加份数 控件
    private ImageView iv_sub;                 //减少份数 控件
    private ImageView zfb_img;                //支付图片 控件
    private ImageView wx_img;                 //微信图片 控件

    private Button view_doc;                  //预览文档 按钮
    private Button pay;                       //支付     按钮

    private String docUrl;                    //文档在服务器的地址
    private String docUri;                    //文档在手机中的定位
    private Intent intent;

    private int payPattern;                   //支付方式 0--支付宝支付，1--微信支付
    private boolean selectLoaction=false;     //打印地点是否选择
    private POrder order=new POrder();        //订单信息
    private PrintPresenter printPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        printPresenter=new PrintPresenter(this);
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
        tv_address=findViewById(R.id.tv_address);
        tv_filename=findViewById(R.id.tv_filename);
        tv_page=findViewById(R.id.tv_total_pages);
        tv_univalence=findViewById(R.id.tv_totals_price);
        tv_number=findViewById(R.id.tv_number);
        tv_price=findViewById(R.id.tv_money);
        iv_add=findViewById(R.id.print_number_add);
        iv_sub=findViewById(R.id.print_number_sub);
        zfb_img=findViewById(R.id.iv_zfb_pay);
        wx_img=findViewById(R.id.iv_weixin_pay);
        zfb_item=findViewById(R.id.cl_zfb);
        wx_item=findViewById(R.id.cl_wx);

        view_doc=findViewById(R.id.print_view);
        pay=findViewById(R.id.print_cost);

        tv_filename.setText(filename);
        tv_page.setText(filepage);
        tv_univalence.setText(PrintPresenter
                .countUnivalence(tv_page));
        tv_price.setText(PrintPresenter
                .countPrice(tv_number,tv_page));

        zfb_item.setOnClickListener(this);
        wx_item.setOnClickListener(this);
        select_location.setOnClickListener(this);
        iv_add.setOnClickListener(this);
        iv_sub.setOnClickListener(this);
        zfb_img.setOnClickListener(this);
        wx_img.setOnClickListener(this);
        view_doc.setOnClickListener(this);
        pay.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.rl_address:                                              //选择打印地点
                selectLoaction=true;
                printPresenter.selectLocation(this);                   //启动位置列表活动
                break;
            case R.id.print_view:                                              //点击预览按钮
                ActivityUtil.checkDoc(this,docUri);
                break;
            case R.id.print_number_add:                                        //点击增加按钮
                printPresenter.numberChange(tv_number,1);               //更新份数UI
                tv_price.setText(PrintPresenter                                //更新合计价格UI
                        .countPrice(tv_number,tv_page));
                break;
            case R.id.print_number_sub:                                        //点击减少按钮
                printPresenter.numberChange(tv_number,0);
                tv_price.setText(PrintPresenter                                //更新合计价格UI
                        .countPrice(tv_number,tv_page));
                break;
            case R.id.cl_zfb:
                payPattern=0;
                printPresenter.payPatternChange(zfb_img,wx_img,0);       //点击支付宝支付
                break;
            case R.id.cl_wx:
                payPattern=1;
                printPresenter.payPatternChange(zfb_img,wx_img,1);       //点击微信支付
                break;
            case R.id.print_cost:                                               //支付按钮
                if(selectLoaction){
                    parsePOrder();                                              //打包POrder类数据
                    printPresenter.payCost(order);                                   //支付接口，发送消息至服务器
                }else{
                    printPresenter.tipLocation();                               //提示地点未选择
                }
                break;
        }
    }
    //接收位置活动返回的信息
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        switch(requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    String locationId = data.getStringExtra("locationId");
                    String locationName = data.getStringExtra("locationName");
                    LogUtil.d("位置信息",
                            "PrintActivity活动 locationId = "+locationId);
                    LogUtil.d("位置信息",
                            "PrintActivity活动 locatioName = "+locationName);
                    tv_address.setText(locationName);                               //更新位置UI
                    selectLoaction = true;                                          //更新选择情况
                    order.setAwhere(locationId);
                }
            case 2:
                break;
            default:
                break;
        }
    }
    //打包POder类数据
    private void parsePOrder(){
        docUrl=docUrl.replace('\\','/');
        order.setAid("admin");
        order.setAtoken("aa20190718211933");
        order.setAurl(docUrl);
        order.setAstatus("-1");
        order.setAcost(tv_price.getText().toString());
        order.setAnumber(tv_number.getText().toString());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date=new Date(System.currentTimeMillis());
        order.setAtime(simpleDateFormat.format(date));
        order.viewInfo();
    }
}
