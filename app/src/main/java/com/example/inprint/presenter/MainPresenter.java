package com.example.inprint.presenter;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.inprint.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;
/*
 * @Author Wenpupil
 * @Description MainActivity的对象中间件
 */
public class MainPresenter {
    private Activity activity;
    private ImageView[] tabIcons=new ImageView[3];  //tab1的图标
    private TextView[] tabTexts=new TextView[3];   //tab1的文本

    private int[] pastIcon={R.mipmap.wendang1,R.mipmap.order1,R.mipmap.wode1};
    private int[] nowIcon={R.mipmap.wendang,R.mipmap.order,R.mipmap.wode};
    public MainPresenter(Activity activity) {
        this.activity=activity;
        tabIcons[0]=activity.findViewById(R.id.iv_tab1);
        tabIcons[1]=activity.findViewById(R.id.iv_tab2);
        tabIcons[2]=activity.findViewById(R.id.iv_tab3);

        tabTexts[0]=activity.findViewById(R.id.tv_tab1);
        tabTexts[1]=activity.findViewById(R.id.tv_tab2);
        tabTexts[2]=activity.findViewById(R.id.tv_tab3);
    }

    //改变tab的图标和文字颜色
    public int changeTab(int past,int now){
        tabIcons[past].setImageResource(pastIcon[past]);
        tabIcons[now].setImageResource(nowIcon[now]);
        tabTexts[past].setTextColor(activity.
                getResources().getColor(R.color.app_grey));
        tabTexts[now].setTextColor(activity.
                getResources().getColor(R.color.app_icon_color));
        return now;
    }
    //初始化轮播广告控件
    public static void iniBanner(Banner banner){
        List<Integer> data=new ArrayList<>();
        data.add(R.mipmap.l1);
        data.add(R.mipmap.l2);
        data.add(R.mipmap.l3);
        banner.setImageLoader(new GlideImageLoader());
        //设置轮播图片集合
        banner.setImages(data);
        //设置轮播时间
        banner.setDelayTime(2000);
        banner.start();
    }
}
